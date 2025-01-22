package com.ERP.erp_api.domain;

public class MaterialRequest {

    private Integer materialrequestId;
    private Integer materialNumber;
    private Integer createdBy;
    private Integer approved_by;
    private Integer delete_by;
    private String status;
    private String description_rejected;
    private Integer totalItem;
    private String username;
    private String createdAt;
    
    public MaterialRequest(Integer materialrequestId, Integer materialNumber, Integer createdBy, Integer approved_by,
            Integer delete_by, String status, String description_rejected, Integer totalItem, String username,
            String createdAt) {
        this.materialrequestId = materialrequestId;
        this.materialNumber = materialNumber;
        this.createdBy = createdBy;
        this.approved_by = approved_by;
        this.delete_by = delete_by;
        this.status = status;
        this.description_rejected = description_rejected;
        this.totalItem = totalItem;
        this.username = username;
        this.createdAt = createdAt;
    }

    public Integer getMaterialrequestId() {
        return materialrequestId;
    }

    public void setMaterialrequestId(Integer materialrequestId) {
        this.materialrequestId = materialrequestId;
    }

    public Integer getMaterialNumber() {
        return materialNumber;
    }

    public void setMaterialNumber(Integer materialNumber) {
        this.materialNumber = materialNumber;
    }

    public Integer getCreatedBy() {
        return createdBy;
    }

    public void setCreatedBy(Integer createdBy) {
        this.createdBy = createdBy;
    }

    public Integer getApproved_by() {
        return approved_by;
    }

    public void setApproved_by(Integer approved_by) {
        this.approved_by = approved_by;
    }

    public Integer getDelete_by() {
        return delete_by;
    }

    public void setDelete_by(Integer delete_by) {
        this.delete_by = delete_by;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription_rejected() {
        return description_rejected;
    }

    public void setDescription_rejected(String description_rejected) {
        this.description_rejected = description_rejected;
    }

    public Integer getTotalItem() {
        return totalItem;
    }

    public void setTotalItem(Integer totalItem) {
        this.totalItem = totalItem;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    
    
}
