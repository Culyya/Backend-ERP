package com.ERP.erp_api.services;

import java.util.List;

import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface RoleService {

     List<Role> fetchAllRole();
    
     Role fetchRoleById(Integer roleId) throws EtResourceNotFoundException;

     Role addRole(String role_name) throws EtBadRequestException;

     void updateRole( Integer roleId, Role role) throws EtBadRequestException;

     void removeRole(Integer roleId) throws EtResourceNotFoundException;
}
