package com.ERP.erp_api.repositories;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;


import com.ERP.erp_api.domain.User;
import com.ERP.erp_api.exceptions.EtAuthException;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;

@Repository
public class UserRepositoryImpl implements UserRepository  {
   

    private static final String SQL_ALL = "SELECT A.USER_ID,A.USERNAME,A.EMAIL,A.PASSWORD,A.DEPARTEMENT_ID,C.DEPARTEMENT_NAME,B.ROLE_NAME,A.ROLE_ID FROM USERS A LEFT JOIN ROLE B ON A.ROLE_ID =B.ROLE_ID LEFT JOIN DEPARTEMENT C ON A.DEPARTEMENT_ID = C.DEPARTEMENT_ID";
    private static final String SQL_STRING = "INSERT INTO USERS(USERNAME,EMAIL,PASSWORD,DEPARTEMENT_ID,ROLE_ID) VALUES(?,?,?,?,?)";
    private static final String SQL_COUNT_BY_USER = "SELECT COUNT(*) FROM USERS WHERE USERNAME=?";
    private static final String SQL_FIND_BY_ID = "SELECT A.USER_ID,A.USERNAME,A.EMAIL,A.PASSWORD,A.DEPARTEMENT_ID,C.DEPARTEMENT_NAME,B.ROLE_NAME,A.ROLE_ID FROM USERS A LEFT JOIN ROLE B ON A.ROLE_ID =B.ROLE_ID LEFT JOIN DEPARTEMENT C ON A.DEPARTEMENT_ID = C.DEPARTEMENT_ID WHERE A.USER_ID=?";
    private static final String SQL_FIND_BY_EMAIL = "SELECT A.USER_ID,A.USERNAME,A.EMAIL,A.PASSWORD,A.DEPARTEMENT_ID,C.DEPARTEMENT_NAME,B.ROLE_NAME,A.ROLE_ID FROM USERS A LEFT JOIN ROLE B ON A.ROLE_ID =B.ROLE_ID LEFT JOIN DEPARTEMENT C ON A.DEPARTEMENT_ID = C.DEPARTEMENT_ID WHERE A.EMAIL=?";
    private static final String SQL_DELETE = "DELETE FROM USERS WHERE USER_ID=?";

    @Autowired
    JdbcTemplate JdbcTemplate;

    @Override
    public Integer create(String username, String email, String password, Integer departementId, Integer roleId) throws EtAuthException{
       String hashedPassword = BCrypt.hashpw(password, BCrypt.gensalt(10));
        try {
            KeyHolder KeyHolder = new GeneratedKeyHolder();
            JdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_STRING, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,username);
                ps.setString(2,email);
                ps.setString(3,hashedPassword);
                ps.setInt(4,departementId);
                ps.setInt(5,roleId);
                return ps;
            }, KeyHolder);
            return (Integer) KeyHolder.getKeys().get("USER_ID");
        } catch (Exception e) {
          throw new EtAuthException("Invalid Details.Failed To Create Account");
        }
    }

    @Override
    public User findByUsername(String username) throws EtAuthException {
        return null;
    }

    @Override
    public User findByEmailAndPassword( String email, String password) throws EtAuthException {
        try {
            User user = JdbcTemplate.queryForObject(SQL_FIND_BY_EMAIL, new Object[]{email},userRowMapper);
            if (!BCrypt.checkpw(password,user.getPassword()))
                throw new EtAuthException("Invalid email/password");
            return user;
        } catch (EmptyResultDataAccessException e) {
           throw new EtAuthException("Invalid email/password");
        }
   
    }

     @Override
    public List<User> findAll() throws EtResourceNotFoundException {
        return JdbcTemplate.query(SQL_ALL, new Object[]{}, userRowMapper);
    }


    @Override
    public Integer getCountUsername(String username){
        return JdbcTemplate.queryForObject(SQL_COUNT_BY_USER, new Object[]{username}, Integer.class);
    }

    @Override
    public User findById(Integer userId){
        return JdbcTemplate.queryForObject(SQL_FIND_BY_ID, new Object[]{userId}, userRowMapper);
    }

     @Override
    public void removeById(Integer userId) throws EtBadRequestException {
        try {
            JdbcTemplate.update(SQL_DELETE, new Object[]{userId});
          } catch (Exception e) {
             throw new EtResourceNotFoundException("Invalid Request");
          }
    }


   private RowMapper<User> userRowMapper = ((rs, rowNum) -> {
       return new User(rs.getInt("USER_ID"),
               rs.getString("USERNAME"),
               rs.getString("EMAIL"),
               rs.getString("PASSWORD"),
               rs.getInt("DEPARTEMENT_ID"),
               rs.getString("DEPARTEMENT_NAME"),
               rs.getString("ROLE_NAME"),
               rs.getInt("ROLE_ID"), null);
   });
}
