package com.ERP.erp_api.repositories;


import java.util.List;

import com.ERP.erp_api.domain.MaterialRequest;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface MaterialRequestRepository {

    List<MaterialRequest> findAll() throws EtResourceNotFoundException;

    MaterialRequest findById(Integer materialrequestId) throws EtResourceNotFoundException;

    Integer create(Integer createdBy) throws EtBadRequestException;

    void update( Integer materialrequestId, MaterialRequest materialRequest) throws EtBadRequestException;

    void updateS(Integer materialrequestId,MaterialRequest materialRequest) throws EtBadRequestException;

    void removeById(Integer materialrequestId,MaterialRequest materialRequest)  throws EtBadRequestException;
}

