package com.ERP.erp_api.resources;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ERP.erp_api.domain.Role;
import com.ERP.erp_api.services.RoleService;
import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/role")

public class RoleResource {

     @Autowired
    RoleService roleService;

    @GetMapping("")
    public ResponseEntity<List<Role>>  getAllRole(HttpServletRequest request){
        List<Role> role = roleService.fetchAllRole();
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

    @GetMapping("/{roleId}")
    public ResponseEntity<Role> getRoleById(HttpServletRequest request, @PathVariable("role_id") Integer roleId){
        Role role = roleService.fetchRoleById(roleId);
        return new ResponseEntity<>(role,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Role> addRole(HttpServletRequest request, @RequestBody Map<String, Object> rolemap){
        String role_name = (String) rolemap.get("role_name");
        Role role = roleService.addRole(role_name);
        return  new ResponseEntity<>(role,HttpStatus.CREATED);
    }

    @PutMapping("/{roleId}")
    public ResponseEntity<Map<String, Boolean>> updateRole(HttpServletRequest request, @PathVariable("roleId") Integer roleId, @RequestBody Role role) {
        roleService.updateRole(roleId, role);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{roleId}")
    public ResponseEntity<Map<String, Boolean>> removeRole(HttpServletRequest request, @PathVariable("roleId") Integer roleId){
        roleService.removeRole(roleId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
