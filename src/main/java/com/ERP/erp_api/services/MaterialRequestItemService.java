package com.ERP.erp_api.services;

import java.util.List;
import java.util.Map;

import com.ERP.erp_api.domain.MaterialRequestItem;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface MaterialRequestItemService {
     List<MaterialRequestItem> fetchAllMaterialRequestItems();

     List<MaterialRequestItem> fetchMaterialRequestItemsById(Integer materialrequestId) throws EtResourceNotFoundException;

     List<Integer> create(List<Map<String, Object>> materialRequestItems) throws EtBadRequestException;

     void updateMultipleMaterialRequests(List<MaterialRequestItem> materialRequestItems) throws EtBadRequestException;

    void removeMaterialRequest(Integer materialrequestitemId) throws EtResourceNotFoundException;
}
