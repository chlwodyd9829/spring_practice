package com.example.spring.practice.controller;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;
import com.example.spring.practice.domain.member.*;
import com.example.spring.practice.domain.order.Order;
import com.example.spring.practice.domain.order.OrderDetail;
import com.example.spring.practice.domain.order.OrderState;
import com.example.spring.practice.service.UploadFile;
import com.example.spring.practice.service.item.ItemService;
import com.example.spring.practice.service.member.MemberService;
import com.example.spring.practice.service.order.OrderService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
@RequestMapping("/admin")
public class AdminController {
    private final MemberService memberService;
    private final ItemService itemService;

    private final OrderService orderService;
    @ModelAttribute("classifications")
    private Classification[] classifications(){
        return Classification.values();
    }
    @ModelAttribute("orderStates")
    private OrderState[] orderStates(){
        return OrderState.values();
    }
    @GetMapping
    public String home(HttpServletRequest request, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute("loginMember");
        model.addAttribute("loginMember",loginMember);
        List<String> colNames = memberService.colNames();
        List<Member> members = memberService.members();
        model.addAttribute("colNames",colNames);
        model.addAttribute("members",members);

        List<Item> items = itemService.items();
        model.addAttribute("items",items);

        List<Order> orders = orderService.orders();
        model.addAttribute("orders",orders);
        return "admin/home";
    }
    @GetMapping("login")
    public String login(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "admin/login";
    }

    @PostMapping("login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "admin/login";
        }
        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
        if(loginMember == null){
            bindingResult.reject("loginFail");
            return "admin/login";
        }
        HttpSession session = request.getSession();
        session.setAttribute("loginMember",loginMember);
        return "redirect:/admin";
    }
    @PostMapping("/logout")
    public String logout(HttpServletRequest request,@ModelAttribute LoginForm loginForm){
        HttpSession session = request.getSession(false);
        if(session != null){
            session.invalidate();
        }
        return "redirect:/admin/login";
    }
    @GetMapping("member/{id}")
    public String member(@PathVariable String id,Model model){
        Member member = memberService.member(id);
        UpdateMember updateMember = new UpdateMember(member.getId(), member.getPassword(), member.getName(), member.getAddress(), member.getClassification());
        model.addAttribute("updateMember",updateMember);
        return "admin/member/member";
    }
    @PostMapping("member/{id}")
    public String member(@PathVariable String id, @Validated @ModelAttribute UpdateMember updateMember, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "admin/member/{id}";
        }
        memberService.update(updateMember);
        return "redirect:/admin";
    }
    @GetMapping("items/{id}")
    public String item(@PathVariable String id,Model model){
        Item item = itemService.item(id);
        List<String> storeFileNames = new ArrayList<>();
        for (UploadFile uploadFile : item.getUploadFileList()) {
            storeFileNames.add(uploadFile.getStoreFileName());
        }
        UpdateItem updateItem = new UpdateItem(item.getUploadFile().getStoreFileName(),storeFileNames,item.getId(), item.getName(), item.getPrice(), item.getQuantity(), item.getInfo());
        model.addAttribute("updateItem",updateItem);
        return "admin/items/item";
    }
    @PostMapping("items/{id}")
    public String item(Model model,@PathVariable String id, @Validated @ModelAttribute UpdateItem updateItem,BindingResult bindingResult){
        log.info("call update item");
        if(bindingResult.hasErrors()){
            log.info("updateItem.uploadFile={}",updateItem.getUploadFile());
            return "admin/items/item";
        }
        itemService.updateItem(updateItem);
        return "redirect:/admin";
    }

    @GetMapping("items/new")
    public String new_item(@ModelAttribute NewItem newItem){
        return "admin/items/newItem";
    }

    @PostMapping("items/new")
    public String new_Item(@Validated @ModelAttribute NewItem newItem, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "admin/items/newItem";
        }
        itemService.newItem(newItem);
        return "redirect:/admin";
    }

    @GetMapping("orders/{id}")
    public String orderDetail(@PathVariable String id, Model model){
        List<OrderDetail> orderDetails = orderService.findOrderDetail(id);
        Order order = orderService.findOrder(id);
        for (OrderDetail orderDetail : orderDetails) {
            log.info("orderDetail.UploadFile ={}",orderDetail.getUploadFile());
        }
        model.addAttribute("order",order);
        model.addAttribute("orderDetails",orderDetails);
        return "admin/orders/order";
    }
    @PostMapping("orders/{id}")
    public String orderDetail(@ModelAttribute Order order){
        orderService.changeState(order.getId(),order.getOrderState());
        return "redirect:/admin";
    }
}
