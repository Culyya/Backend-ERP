package com.ERP.erp_api.repositories;

import java.util.List;
import java.sql.PreparedStatement;
import java.sql.Statement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ERP.erp_api.domain.Item;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;


@Repository
public class ItemRepositoryImpl implements ItemRepository {

    private static final String SQL_CREATE = "INSERT INTO ITEM (ITEM_NAME) VALUES(?)";
    private static final String SQL_FIND_ID = "SELECT * FROM ITEM WHERE ITEM_ID=?";
    private static final String SQL_ALL = "SELECT * FROM ITEM";
    private static final String SQL_UPDATE = "UPDATE ITEM SET ITEM_NAME=?,UPDATE_AT=NOW() WHERE ITEM_ID=?";
    private static final String SQL_DELETE = "DELETE FROM ITEM WHERE ITEM_ID=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String item_name) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, item_name);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("item_id");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request");
        }
    }

    @Override
    public List<Item> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_ALL, new Object[]{}, itemRowMapper);
    }

    @Override
    public Item findById(Integer itemId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[]{itemId}, itemRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Items Not Found");
        }
    }

    @Override
    public void removeById(Integer itemId) throws EtBadRequestException {
      try {
        jdbcTemplate.update(SQL_DELETE, new Object[]{itemId});
      } catch (Exception e) {
         throw new EtResourceNotFoundException("Invalid Request");
      }
    }

    @Override
    public void update(Integer itemId, Item item) throws EtBadRequestException {
       try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{item.getItem_name(),itemId});
       } catch (Exception e) {
        throw new EtResourceNotFoundException("Invalid Request");
       }
    }

    private RowMapper<Item> itemRowMapper = ((rs, rowNum) -> {
       return new Item(rs.getInt("ITEM_ID"),
               rs.getString("ITEM_NAME"), null);
   });

}
