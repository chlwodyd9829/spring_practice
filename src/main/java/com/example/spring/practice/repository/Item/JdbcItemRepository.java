package com.example.spring.practice.repository.Item;


import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;
import com.example.spring.practice.service.UploadFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class JdbcItemRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcItemRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Item save(Item item) {
        String sql = "insert into item values(?,?,?,?,?,?,?)";
        jdbcTemplate.update(sql,item.getId(), item.getName(),item.getPrice(),item.getQuantity(),item.getInfo(),item.getUploadFile().getStoreFileName(),item.getUploadFile().getUploadFileName());
        String saveFilesSql = "insert into uploadFiles values(?,?,?)";
        for (UploadFile uploadFile : item.getUploadFileList()) {
            jdbcTemplate.update(saveFilesSql,item.getId(),uploadFile.getUploadFileName(),uploadFile.getStoreFileName());
        }
        return item;
    }

    @Override
    public Item findById(String id) {
        String sql = "select * from item where id=?";
        List<Item> result = jdbcTemplate.query(sql, itemRowMapper(), id);
        for (Item item : result) {
            String uploadFileSql = "select * from uploadFiles where id = ?";
            List<UploadFile> uploadFiles = jdbcTemplate.query(uploadFileSql, uploadFileRowMapper(), item.getId());
            item.setUploadFileList(uploadFiles);
        }
        return result.stream().findAny().orElse(null);
    }

    private RowMapper<Item> itemRowMapper(){
        return (rs, rowNum)->{
            Item item = new Item();
            item.setId(rs.getString("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            item.setInfo(rs.getString("info"));
            UploadFile uploadFile = new UploadFile(rs.getString("uploadFileName"), rs.getString("storeFileName"));
            item.setUploadFile(uploadFile);
            return item;
        };
    }
    private RowMapper<UploadFile> uploadFileRowMapper(){
        return (rs,rowNum)->{
            UploadFile uploadFile = new UploadFile(rs.getString("uploadFileName"), rs.getString("storeFileName"));
            return uploadFile;
        };
    }
    @Override
    public List<Item> findAll() {
        String sql = "select * from item";
        List<Item> result = jdbcTemplate.query(sql, itemRowMapper());
        String uploadFileSql="select * from uploadFiles where id = ?";
        for (Item item : result) {
            List<UploadFile> uploadFiles = jdbcTemplate.query(uploadFileSql, uploadFileRowMapper(), item.getId());
            item.setUploadFileList(uploadFiles);
        }
        return result;
    }

    @Override
    public void updateItem(Item item) {
        String sql = "update item set name=?,price=?,quantity=?,info=? where id=?";
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getQuantity(), item.getInfo(),item.getId());
    }
}
