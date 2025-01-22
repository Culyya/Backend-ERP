package com.ERP.erp_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.erp_api.domain.MaterialRequest;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.MaterialRequestRepository;

@Service
@Transactional

public class MaterialRequestServiceImpl implements MaterialRequestService {

    @Autowired
    MaterialRequestRepository materialRequestRepository;

    @Override
    public MaterialRequest addMaterialRequest( Integer createdBy)
            throws EtBadRequestException {
        int materialrequestId = materialRequestRepository.create(createdBy);
        return materialRequestRepository.findById(materialrequestId);
    }

    @Override
    public List<MaterialRequest> fetchAllMaterialRequest() {
        return materialRequestRepository.findAll();
    }

    @Override
    public MaterialRequest fetchMaterialRequestById(Integer materialrequestId) throws EtResourceNotFoundException {
        return materialRequestRepository.findById(materialrequestId);
    }

    @Override
    public void removeMaterialRequest(Integer materialrequestId, MaterialRequest materialRequest)
            throws EtResourceNotFoundException {
        materialRequestRepository.removeById(materialrequestId, materialRequest);
    }

    @Override
    public void updateMaterialRequest(Integer materialrequestId, MaterialRequest materialRequest)
            throws EtBadRequestException {
       materialRequestRepository.update(materialrequestId, materialRequest);
    }

    @Override
    public void updateStatus(Integer materialrequestId, MaterialRequest materialRequest)
            throws EtBadRequestException {
        materialRequestRepository.updateS(materialrequestId, materialRequest);   
    }

}
