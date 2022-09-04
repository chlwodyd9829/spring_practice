package com.example.spring.practice.repository.Item;


import com.example.spring.practice.domain.item.Item;
import com.example.spring.practice.domain.item.NewItem;
import com.example.spring.practice.domain.item.UpdateItem;
import com.example.spring.practice.service.UploadFile;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;
import java.util.Optional;

public class JdbcItemRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcItemRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Item save(Item item) {
        String sql = "insert into item(name,price,quantity,info,storeFileName,uploadFileName) values(?,?,?,?,?,?)";
        jdbcTemplate.update(sql,item.getName(),item.getPrice(),item.getQuantity(),item.getInfo(),item.getUploadFile().getStoreFileName(),item.getUploadFile().getUploadFileName());
        return item;
    }

    @Override
    public Optional<Item> findById(Long id) {
        String sql = "select * from item where id=?";
        List<Item> result = jdbcTemplate.query(sql, itemRowMapper(), id);
        return result.stream().findAny();
    }

    private RowMapper<Item> itemRowMapper(){
        return (rs, rowNum)->{
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            item.setInfo(rs.getString("info"));
            UploadFile uploadFile = new UploadFile(rs.getString("uploadFileName"), rs.getString("storeFileName"));
            item.setUploadFile(uploadFile);
            return item;
        };
    }

    @Override
    public List<Item> findAll() {
        String sql = "select * from item";
        return jdbcTemplate.query(sql, itemRowMapper());
    }

    @Override
    public void updateItem(Item item) {
        String sql = "update item set name=?,price=?,quantity=?,info=? where id=?";
        jdbcTemplate.update(sql, item.getName(), item.getPrice(), item.getQuantity(), item.getInfo(),item.getId());
    }
}
