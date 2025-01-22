package com.ERP.erp_api.services;

import java.util.List;

import com.ERP.erp_api.domain.MaterialRequest;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface MaterialRequestService {

    List<MaterialRequest> fetchAllMaterialRequest();
    
    MaterialRequest fetchMaterialRequestById(Integer materialrequestId) throws EtResourceNotFoundException;

    MaterialRequest addMaterialRequest(Integer createdBy) throws EtBadRequestException;

    void updateMaterialRequest( Integer materialrequestId, MaterialRequest materialRequest) throws EtBadRequestException;

    void updateStatus(Integer materialrequestId,MaterialRequest materialRequest) throws EtBadRequestException;

    void removeMaterialRequest(Integer materialrequestId,MaterialRequest materialRequest) throws EtResourceNotFoundException;
}
