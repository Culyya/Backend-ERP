package com.ERP.erp_api.repositories;

import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.ERP.erp_api.domain.MaterialRequestItem;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

@Repository
public class MaterialRequestItemRepositoryImpl implements MaterialRequestItemRepository {

    private static final String SQL_CREATE = "INSERT INTO MATERIAL_REQUEST_ITEM (MATERIAL_REQUEST_ID,ITEM_ID,QUANTITY,DESCRIPTION) VALUES (?,?,?,?)";
    private static final String SQL_FIND_ID = "SELECT A.MATERIAL_REQUEST_ITEM_ID,A.MATERIAL_REQUEST_ID,B.ITEM_NAME,A.ITEM_ID,A.QUANTITY,A.DESCRIPTION FROM MATERIAL_REQUEST_ITEM A LEFT JOIN ITEM B ON A.ITEM_ID = B.ITEM_ID WHERE A.MATERIAL_REQUEST_ID = ?";
    private static final String SQL_ALL = "SELECT A.MATERIAL_REQUEST_ITEM_ID,A.MATERIAL_REQUEST_ID,B.ITEM_NAME,A.ITEM_ID,A.QUANTITY,A.DESCRIPTION FROM MATERIAL_REQUEST_ITEM A LEFT JOIN ITEM B ON A.ITEM_ID = B.ITEM_ID";
    private static final String SQL_UPDATE = "UPDATE MATERIAL_REQUEST_ITEM SET QUANTITY=?, DESCRIPTION=?,UPDATE_AT=NOW() WHERE MATERIAL_REQUEST_ITEM_ID=?";
    private static final String SQL_DELETE = "DELETE FROM MATERIAL_REQUEST_ITEM WHERE MATERIAL_REQUEST_ITEM_ID=?";

    @Autowired
    JdbcTemplate jdbcTemplate;

    // @Override
    // public Integer create(Integer materialrequestId,Integer itemId, Integer
    // quantity, String description)
    // throws EtBadRequestException {
    // try {
    // KeyHolder keyHolder = new GeneratedKeyHolder();
    // jdbcTemplate.update(connection -> {
    // PreparedStatement ps = connection.prepareStatement(SQL_CREATE,
    // Statement.RETURN_GENERATED_KEYS);
    // ps.setInt(1, materialrequestId);
    // ps.setInt(2, itemId);
    // ps.setInt(3, quantity);
    // ps.setString(4, description);
    // return ps;
    // },keyHolder);
    // return (Integer) keyHolder.getKeys().get("material_request_item_id");
    // } catch (Exception e) {
    // throw new EtBadRequestException("Invalid Request" + e.getMessage());
    // }
    // }

    @Override
    public List<Integer> create(List<Map<String, Object>> materialRequestItems) throws EtBadRequestException {
        List<Integer> createdIds = new ArrayList<>();
        try {
            // Loop melalui semua item dan insert satu per satu
            for (Map<String, Object> item : materialRequestItems) {
                Integer materialRequestId = (Integer) item.get("material_request_id");
                Integer itemId = (Integer) item.get("item_id");
                Integer quantity = (Integer) item.get("quantity");
                String description = (String) item.get("description");

                // Insert satu item dan ambil generated ID
                Integer generatedId = insertMaterialRequestItem(materialRequestId, itemId, quantity, description);
                createdIds.add(generatedId);
            }
        } catch (Exception e) {
            throw new EtBadRequestException("Error inserting material request items: " + e.getMessage());
        }
        return createdIds;
    }

    private Integer insertMaterialRequestItem(Integer materialRequestId, Integer itemId, Integer quantity,
            String description) throws EtBadRequestException {
        try {
            KeyHolder keyHolder = new GeneratedKeyHolder();
            jdbcTemplate.update(connection -> {
                PreparedStatement ps = connection.prepareStatement(SQL_CREATE, Statement.RETURN_GENERATED_KEYS);
                ps.setInt(1, materialRequestId);
                ps.setInt(2, itemId);
                ps.setInt(3, quantity);
                ps.setString(4, description);
                return ps;
            }, keyHolder);
            return (Integer) keyHolder.getKeys().get("MATERIAL_REQUEST_ITEM_ID");
        } catch (Exception e) {
            throw new EtBadRequestException("Error inserting material request item: " + e.getMessage());
        }
    }

    @Override
    public List<MaterialRequestItem> findAll() throws EtResourceNotFoundException {
        return jdbcTemplate.query(SQL_ALL, new Object[] {}, materialRowMapper);
    }

    @Override
    public List<MaterialRequestItem> findById(Integer materialrequestId) throws EtResourceNotFoundException {
        try {
            return jdbcTemplate.query(SQL_FIND_ID, new Object[] { materialrequestId }, materialRowMapper);
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Material Request Not Found");
        }
    }

    // @Override
    // public MaterialRequestItem findById(Integer materialrequestId) throws
    // EtResourceNotFoundException {
    // try {
    // return jdbcTemplate.queryForObject(SQL_FIND_ID, new
    // Object[]{materialrequestId}, materialRowMapper);
    // } catch (Exception e) {
    // throw new EtResourceNotFoundException("Material Request Not Found");
    // }
    // }

    @Override
    public void removeById(Integer materialrequestitemId) throws EtBadRequestException {
        try {
            jdbcTemplate.update(SQL_DELETE, new Object[] { materialrequestitemId });
        } catch (Exception e) {
            throw new EtResourceNotFoundException("Invalid Request");
        }
    }



@Override
public void updateBatch(List<MaterialRequestItem> materialRequestItems) throws EtBadRequestException {
    String sql = "UPDATE material_request_item SET quantity = ?, description = ? WHERE material_request_item_id = ?";
    try {
        jdbcTemplate.batchUpdate(sql, materialRequestItems, materialRequestItems.size(), (ps, item) -> {
            ps.setInt(1, item.getQuantity());
            ps.setString(2, item.getDescription());
            ps.setInt(3, item.getMaterialrequestitemId());
        });
    } catch (Exception e) {
        throw new EtBadRequestException("Failed to update material request items in batch: " + e.getMessage());
    }
}


    private RowMapper<MaterialRequestItem> materialRowMapper = ((rs, rowNum) -> {
        return new MaterialRequestItem(
                rs.getInt("MATERIAL_REQUEST_ITEM_ID"),
                rs.getInt("MATERIAL_REQUEST_ID"),
                rs.getString("ITEM_NAME"),
                rs.getInt("ITEM_ID"),
                rs.getInt("QUANTITY"),
                rs.getString("DESCRIPTION"));
    });

}
