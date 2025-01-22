package com.ERP.erp_api.repositories;


import java.util.List;
import java.util.Map;

import com.ERP.erp_api.domain.MaterialRequestItem;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface MaterialRequestItemRepository {

    List<MaterialRequestItem> findAll() throws EtResourceNotFoundException;

    List<MaterialRequestItem> findById(Integer materialrequestId) throws EtResourceNotFoundException;

    List<Integer> create(List<Map<String, Object>> materialRequestItems) throws EtBadRequestException;

    void updateBatch(List<MaterialRequestItem> materialRequestItems) throws EtBadRequestException;

    void removeById(Integer materialrequestitemId)  throws EtBadRequestException;
}
