package com.example.spring.practice.repository.Item;


import com.example.spring.practice.domain.item.Item;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.util.List;

public class JdbcItemRepository implements ItemRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcItemRepository(DataSource dataSource){
        jdbcTemplate = new JdbcTemplate(dataSource);
    }
    @Override
    public Item save(Item item) {
        String sql = "insert into item(name,price,quantity,info) values(?,?,?,?)";
        jdbcTemplate.update(sql,item.getName(),item.getPrice(),item.getQuantity(),item.getInfo());
        return item;
    }

    @Override
    public Item findById(Long id) {
        String sql = "select * from item where id=?";
        Item item = jdbcTemplate.queryForObject(sql, itemRowMapper(),id);
        return item;
    }

    private RowMapper<Item> itemRowMapper(){
        return (rs, rowNum)->{
            Item item = new Item();
            item.setId(rs.getLong("id"));
            item.setName(rs.getString("name"));
            item.setPrice(rs.getInt("price"));
            item.setQuantity(rs.getInt("quantity"));
            item.setInfo(rs.getString("info"));
            return item;
        };
    }

    @Override
    public List<Item> findAll() {
        String sql = "select * from item";
        return jdbcTemplate.query(sql, itemRowMapper());
    }
}
