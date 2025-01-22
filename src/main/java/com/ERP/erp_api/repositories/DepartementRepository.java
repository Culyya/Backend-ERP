package com.ERP.erp_api.repositories;


import java.util.List;

import com.ERP.erp_api.domain.Departement;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface DepartementRepository {

    List<Departement> findAll() throws EtResourceNotFoundException;

    Departement findById(Integer departementId) throws EtResourceNotFoundException;

    Integer create(String departement_name) throws EtBadRequestException;

    void update( Integer departementId, Departement departement) throws EtBadRequestException;

    void removeById(Integer departementId)  throws EtBadRequestException;

}
