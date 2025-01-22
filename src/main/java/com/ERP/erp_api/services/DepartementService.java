package com.ERP.erp_api.services;

import java.util.List;

import com.ERP.erp_api.domain.Departement;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface DepartementService {

     List<Departement> fetchAllDepartements();
    
     Departement fetchDepartementById(Integer departementId) throws EtResourceNotFoundException;

     Departement addDepartement(String departement_name) throws EtBadRequestException;

    void updateDepartement( Integer departementId, Departement departement) throws EtBadRequestException;

    void removeDepartement(Integer departementId) throws EtResourceNotFoundException;

}
