package com.example.spring.practice.controller;

import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;
import com.example.spring.practice.domain.member.*;
import com.example.spring.practice.service.item.ItemService;
import com.example.spring.practice.service.member.MemberService;
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
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.List;

@Slf4j
@Controller
@RequiredArgsConstructor
public class AdminController {
    private final MemberService memberService;
    private final ItemService itemService;

    @ModelAttribute("classifications")
    private Classification[] classifications(){
        return Classification.values();
    }
    @GetMapping("/admin")
    public String home(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
        HttpSession session = request.getSession(false);
        Member loginMember = (Member)session.getAttribute("loginMember");
        model.addAttribute("loginMember",loginMember);
        List<String> colNames = memberService.colNames();
        List<Member> members = memberService.members();
        model.addAttribute("colNames",colNames);
        model.addAttribute("members",members);

        List<Item> items = itemService.items();
        model.addAttribute("items",items);
        return "admin/home";
    }
    @GetMapping("/login")
    public String login(Model model){
        model.addAttribute("loginForm",new LoginForm());
        return "login";
    }

    @PostMapping("/login")
    public String login(@Validated @ModelAttribute LoginForm loginForm, BindingResult bindingResult, HttpServletRequest request){
        if(bindingResult.hasErrors()){
            return "login";
        }
        Member loginMember = memberService.login(loginForm.getLoginId(), loginForm.getPassword());
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
        return "redirect:/login";
    }
    @GetMapping("/join")
    public String join(JoinForm joinForm, Model model){
        log.info("join request");
        model.addAttribute("joinForm",new JoinForm());
        return "join";
    }
    @PostMapping("/join")
    public String join(@Validated @ModelAttribute JoinForm joinForm,BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return "join";
        }
        Member joinMember = memberService.join(joinForm);
        if(joinMember == null){
            bindingResult.addError(new ObjectError("exist","이미 존재하는 아이디: "+joinForm.getId()));
            return "join";
        }
        return "redirect:/login";
    }
    @GetMapping("/admin/member/{id}")
    public String member(@PathVariable String id,Model model){
        Member member = memberService.member(id);
        UpdateMember updateMember = new UpdateMember(member.getId(), member.getPassword(), member.getName(), member.getAddress(), member.getClassification());
        model.addAttribute("updateMember",updateMember);
        return "admin/member/member";
    }
    @PostMapping("/admin/member/{id}")
    public String member(@PathVariable String id, @Validated @ModelAttribute UpdateMember updateMember, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            return "/admin/member/{id}";
        }
        log.info("classification = {}",updateMember.getClassification());
        memberService.update(updateMember);
        return "redirect:/admin";
    }
    @GetMapping("/admin/items/{id}")
    public String item(@PathVariable Long id,Model model){
        Item item = itemService.item(id);
        UpdateItem updateItem = new UpdateItem(item.getId(), item.getName(), item.getPrice(), item.getQuantity(), item.getInfo());
        model.addAttribute("updateItem",updateItem);
        return "admin/items/item";
    }
    @PostMapping("/admin/items/{id}")
    public String item(@PathVariable Long id, @Validated @ModelAttribute UpdateItem updateItem,BindingResult bindingResult){
        if(bindingResult.hasErrors()){

            return "admin/items/item";
        }
        itemService.updateItem(updateItem);
        return "redirect:/admin";
    }

    @GetMapping("/admin/items/new")
    public String new_item(@ModelAttribute NewItem newItem){
        return "admin/items/newItem";
    }

    @PostMapping("/admin/items/new")
    public String new_Item(@Validated @ModelAttribute NewItem newItem, BindingResult bindingResult) throws IOException {
        if(bindingResult.hasErrors()){
            return "admin/items/newItem";
        }
        itemService.newItem(newItem);
        return "redirect:/admin";
    }
    @ResponseBody
    @GetMapping("/image/{filename}")
    public Resource getImage(@PathVariable String filename) throws MalformedURLException {
        String file = itemService.getFile(filename);
        System.out.println("file = " + file);
        return new UrlResource("file:"+ file);
    }
}
