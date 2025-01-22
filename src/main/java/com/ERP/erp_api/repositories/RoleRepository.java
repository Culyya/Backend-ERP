package com.ERP.erp_api.repositories;


import java.util.List;

import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;


public interface RoleRepository {
    
    List<Role> findAll() throws EtResourceNotFoundException;

    Role findById(Integer roleId) throws EtResourceNotFoundException;

    Integer create(String role_name) throws EtBadRequestException;

    void update( Integer roleId, Role role) throws EtBadRequestException;

    void removeById(Integer roleId)  throws EtBadRequestException;
}
