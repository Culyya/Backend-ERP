package com.ERP.erp_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.erp_api.domain.Departement;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.DepartementRepository;

@Service
@Transactional
public class DepartementServiceImpl implements DepartementService {

    @Autowired
    DepartementRepository departementRepository;

    @Override
    public Departement addDepartement(String departement_name) throws EtBadRequestException {
       int departementId = departementRepository.create(departement_name);
        return departementRepository.findById(departementId);
    }

    @Override
    public List<Departement> fetchAllDepartements() {
        return departementRepository.findAll();
    }

    @Override
    public Departement fetchDepartementById(Integer departementId) throws EtResourceNotFoundException {
        return departementRepository.findById(departementId);
    }

    @Override
    public void removeDepartement(Integer departementId) throws EtResourceNotFoundException {
        departementRepository.removeById(departementId);
        
    }

    @Override
    public void updateDepartement(Integer departementId, Departement departement) throws EtBadRequestException {
      departementRepository.update(departementId, departement);  
    }

}
