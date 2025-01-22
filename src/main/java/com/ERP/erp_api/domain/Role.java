package com.ERP.erp_api.domain;

public class Role {

    private Integer roleId;
    private String role_name;
    public Role(Integer roleId, String role_name) {
        this.roleId = roleId;
        this.role_name = role_name;
    }
    public Integer getRoleId() {
        return roleId;
    }
    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }
    public String getRole_name() {
        return role_name;
    }
    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    
}
