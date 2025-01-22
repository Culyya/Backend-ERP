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

import com.ERP.erp_api.domain.Departement;

import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;


@Repository
public class DepartementRepositoryImpl implements DepartementRepository {

    private static final String SQL_CREATE = "INSERT INTO DEPARTEMENT (DEPARTEMENT_NAME) VALUES(?)";
    private static final String SQL_FIND_ID = "SELECT * FROM DEPARTEMENT WHERE DEPARTEMENT_ID=?";
    private static final String SQL_ALL = "SELECT * FROM DEPARTEMENT";
    private static final String SQL_UPDATE = "UPDATE ITEM SET DEPARTEMENT_NAME=?,UPDATE_AT=NOW() WHERE DEPARTEMENT_ID=?";
    private static final String SQL_DELETE = "DELETE FROM DEPARTEMENT WHERE DEPARTEMENT=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(String departement_name) throws EtBadRequestException {
       try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1, departement_name);
                return ps;
            },keyHolder);
            return (Integer) keyHolder.getKeys().get("departement_id");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request");
        }
    }

    @Override
    public List<Departement> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_ALL, new Object[]{}, departementRowMapper);
    }

    @Override
    public Departement findById(Integer departementId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[]{departementId}, departementRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Departement Not Found");
        }
    }

    @Override
    public void removeById(Integer departementId) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_DELETE, new Object[]{departementId});
          } catch (Exception e) {
             throw new EtResourceNotFoundException("Invalid Request");
          }
    }

    @Override
    public void update(Integer departementId, Departement departement) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_UPDATE, new Object[]{departement.getDepartement_name(),departementId});
       } catch (Exception e) {
        throw new EtResourceNotFoundException("Invalid Request");
       }
    }

     private RowMapper<Departement> departementRowMapper = ((rs, rowNum) -> {
       return new Departement(rs.getInt("DEPARTEMENT_ID"),
               rs.getString("DEPARTEMENT_NAME"));
   });

}
