package com.ERP.erp_api.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

@Repository
public class RoleRepositoryImpl implements RoleRepository {

    private static final String SQL_CREATE = "INSERT INTO ROLE (ROLE_NAME) VALUES(?)";
    private static final String SQL_FIND_ID = "SELECT * FROM ROLE WHERE ROLE_ID=?";
    private static final String SQL_ALL = "SELECT * FROM ROLE";
    private static final String SQL_UPDATE = "UPDATE ITEM SET ROLE_NAME=?,UPDATE_AT=NOW() WHERE ROLE_ID=?";
    private static final String SQL_DELETE = "DELETE FROM ROLE WHERE ROLE_ID=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String role_name) throws EtBadRequestException {
         try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, role_name);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("role_id");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request");
        }
    }

    @Override
    public List<Role> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_ALL, new Object[]{}, roleRowMapper);
    }

    @Override
    public Role findById(Integer roleId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[]{roleId}, roleRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Role Not Found");
        }
    }

    @Override
    public void removeById(Integer roleId) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_DELETE, new Object[]{roleId});
          } catch (Exception e) {
             throw new EtResourceNotFoundException("Invalid Request");
          }
    }

    @Override
    public void update(Integer roleId, Role role) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{role.getRole_name(),roleId});
       } catch (Exception e) {
        throw new EtResourceNotFoundException("Invalid Request");
       }
    }

     private RowMapper<Role> roleRowMapper = ((rs, rowNum) -> {
       return new Role(rs.getInt("ROLE_ID"),
               rs.getString("ROLE_NAME"));
   });

}
