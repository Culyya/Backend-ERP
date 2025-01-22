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

import com.ERP.erp_api.domain.MaterialRequest;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

@Repository
public class MaterialRequestRepositoryImpl implements MaterialRequestRepository {

    private static final String SQL_CREATE = "INSERT INTO MATERIAL_REQUEST (MATERIAL_NUMBER, CREATED_BY, STATUS) VALUES (nextval('material_number_seq'::regclass), ?, 'Pending Approval')";
    private static final String SQL_FIND_ID = "SELECT " +
            "    A.MATERIAL_REQUEST_ID, " +
            "    A.MATERIAL_NUMBER, " +
            "    A.CREATED_BY, " +
            "    A.APPROVED_BY, " +
            "    A.DELETE_BY, " +
            "    A.STATUS, " +
            "    A.DESCRIPTION_REJECTED, " +
            "    COUNT(C.MATERIAL_REQUEST_ID) AS totalItem, " +
            "    B.USERNAME AS username, " +
            "    A.CREATED_AT " +
            "FROM MATERIAL_REQUEST A " +
            "LEFT JOIN USERS B ON A.CREATED_BY = B.USER_ID " +
            "LEFT JOIN MATERIAL_REQUEST_ITEM C ON A.MATERIAL_REQUEST_ID = C.MATERIAL_REQUEST_ID " +
            "WHERE A.MATERIAL_REQUEST_ID = ? " +
            "GROUP BY " +
            "    A.MATERIAL_REQUEST_ID, " +
            "    A.MATERIAL_NUMBER, " +
            "    A.CREATED_BY, " +
            "    A.APPROVED_BY, " +
            "    A.DELETE_BY, " +
            "    A.STATUS, " +
            "    A.DESCRIPTION_REJECTED, " +
            "    B.USERNAME, " +
            "    A.CREATED_AT;";
    private static final String SQL_ALL = "SELECT " +
            "    A.MATERIAL_REQUEST_ID, " +
            "    A.MATERIAL_NUMBER, " +
            "    A.CREATED_BY, " +
            "    A.APPROVED_BY, " +
            "    A.DELETE_BY, " +
            "    A.STATUS, " +
            "    A.DESCRIPTION_REJECTED, " +
            "    COUNT(C.MATERIAL_REQUEST_ID) AS totalItem, " +
            "    B.USERNAME AS username, " +
            "    A.CREATED_AT " +
            "FROM MATERIAL_REQUEST A " +
            "LEFT JOIN USERS B ON A.CREATED_BY = B.USER_ID " +
            "LEFT JOIN MATERIAL_REQUEST_ITEM C ON A.MATERIAL_REQUEST_ID = C.MATERIAL_REQUEST_ID " +
            "WHERE A.STATUS IN ('Pending Approval','Rejected','Approved') " +
            "GROUP BY " +
            "    A.MATERIAL_REQUEST_ID, " +
            "    A.MATERIAL_NUMBER, " +
            "    A.CREATED_BY, " +
            "    A.APPROVED_BY, " +
            "    A.DELETE_BY, " +
            "    A.STATUS, " +
            "    A.DESCRIPTION_REJECTED, " +
            "    B.USERNAME, " +
            "    A.CREATED_AT;";
    private static final String SQL_DELETE = "UPDATE MATERIAL_REQUEST SET DELETE_BY = ?, STATUS = 'Delete', UPDATE_AT = NOW() WHERE MATERIAL_REQUEST_ID = ?";
    private static final String SQL_UPDATE_REJECTION = "UPDATE MATERIAL_REQUEST \r\n" + //
            "SET \r\n" + //
            "    APPROVED_BY = ?, \r\n" + //
            "    DESCRIPTION_REJECTED = ?, \r\n" + //
            "    STATUS = 'Rejected', \r\n" + //
            "    APPROVED_AT = NOW(), \r\n" + //
            "    UPDATE_AT = NOW() \r\n" + //
            "WHERE MATERIAL_REQUEST_ID = ?;\r\n" + //
            "";
    private static final String SQL_UPDATE_APPROVAL = "UPDATE MATERIAL_REQUEST \r\n" + //
            "SET \r\n" + //
            "    APPROVED_BY = ?, \r\n" + //
            "    STATUS = 'Approved', \r\n" + //
            "    APPROVED_AT = NOW(), \r\n" + //
            "    UPDATE_AT = NOW() \r\n" + //
            "WHERE MATERIAL_REQUEST_ID = ?;\r\n" + //
            "";

    @Autowired
    JdbcTemplate jdbcTemplate;

    @Override
    public Integer create(Integer createdBy) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, createdBy);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("MATERIAL_REQUEST_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Invalid Request: " + e.getMessage());
        }
    }

    @Override
    public List<MaterialRequest> findAll() throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_ALL, new Object[] {}, materialRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("No Material Requests Found");
        }
    }

    @Override
    public MaterialRequest findById(Integer materialrequestId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.queryForObject(SQL_FIND_ID, new Object[] { materialrequestId }, materialRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Material Request Not Found");
        }
    }

    @Override
    public void removeById(Integer materialrequestId, MaterialRequest materialRequest) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_DELETE, new Object[] { materialRequest.getDelete_by(), materialrequestId });
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid Request");
        }
    }

    @Override
    public void update(Integer materialrequestId, MaterialRequest materialRequest) throws EtBadRequestException {
        // TODO: Implement update logic if necessary
    }

    @Override
    public void updateS(Integer materialrequestId, MaterialRequest materialRequest) throws EtBadRequestException {
        try {
            if (materialRequest.getDescription_rejected() != null
                    && !materialRequest.getDescription_rejected().isEmpty()) {
                jdbcTemplate.update(SQL_UPDATE_REJECTION, new Object[] {
                        materialRequest.getApproved_by(),
                        materialRequest.getDescription_rejected(),
                        materialrequestId
                });
            } else {
                jdbcTemplate.update(SQL_UPDATE_APPROVAL, new Object[] {
                        materialRequest.getApproved_by(),
                        materialrequestId
                });
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new EtResourceNotFoundException(
                    "Failed to execute update for materialRequestId: " + materialrequestId);
        }
    }

    private RowMapper<MaterialRequest> materialRowMapper = (rs, rowNum) -> new MaterialRequest(
            rs.getInt("MATERIAL_REQUEST_ID"),
            rs.getInt("MATERIAL_NUMBER"),
            rs.getInt("CREATED_BY"),
            rs.getInt("APPROVED_BY"),
            rs.getInt("DELETE_BY"),
            rs.getString("STATUS"),
            rs.getString("DESCRIPTION_REJECTED"),
            rs.getInt("totalItem"),
            rs.getString("USERNAME"),
            rs.getString("CREATED_AT"));
}
