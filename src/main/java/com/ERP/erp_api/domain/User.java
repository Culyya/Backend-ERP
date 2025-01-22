package com.ERP.erp_api.domain;

import java.sql.Timestamp;

public class User {

    private Integer userId;
    private String username;
    private String email;
    private String password;
    private Integer departementId;
    private String departement_name;
    private String role_name;
    private Integer roleId;
    private java.sql.Timestamp updateAt;
    
    public User(Integer userId, String username, String email, String password, Integer departementId,
            String departement_name, String role_name, Integer roleId, Timestamp updateAt) {
        this.userId = userId;
        this.username = username;
        this.email = email;
        this.password = password;
        this.departementId = departementId;
        this.departement_name = departement_name;
        this.role_name = role_name;
        this.roleId = roleId;
        this.updateAt = updateAt;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getDepartementId() {
        return departementId;
    }

    public void setDepartementId(Integer departementId) {
        this.departementId = departementId;
    }

    public String getDepartement_name() {
        return departement_name;
    }

    public void setDepartement_name(String departement_name) {
        this.departement_name = departement_name;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public java.sql.Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(java.sql.Timestamp updateAt) {
        this.updateAt = updateAt;
    }


}
