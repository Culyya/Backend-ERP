package com.ERP.erp_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.RoleRepository;


@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    RoleRepository roleRepository;

    @Override
    public Role addRole(String role_name) throws EtBadRequestException {
        int roleId = roleRepository.create(role_name);
      return roleRepository.findById(roleId);
    }

    @Override
    public List<Role> fetchAllRole() {
        return roleRepository.findAll();
    }

    @Override
    public Role fetchRoleById(Integer roleId) throws EtResourceNotFoundException {
        return roleRepository.findById(roleId);
    }

    @Override
    public void removeRole(Integer roleId) throws EtResourceNotFoundException {
        roleRepository.removeById(roleId); 
    }

    @Override
    public void updateRole(Integer roleId, Role role) throws EtBadRequestException {
        roleRepository.update(roleId, role);
    }

}
