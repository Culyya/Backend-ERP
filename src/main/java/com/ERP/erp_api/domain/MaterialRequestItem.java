package com.ERP.erp_api.domain;

public class MaterialRequestItem {
    private Integer materialrequestitemId;
    private Integer materialrequestId;
    private String  itemName;
    private Integer itemId;
    private Integer quantity;
    private String description;

    public MaterialRequestItem(Integer materialrequestitemId, Integer materialrequestId, String itemName,
            Integer itemId, Integer quantity, String description) {
        this.materialrequestitemId = materialrequestitemId;
        this.materialrequestId = materialrequestId;
        this.itemName = itemName;
        this.itemId = itemId;
        this.quantity = quantity;
        this.description = description;
    }

    public Integer getMaterialrequestitemId() {
        return materialrequestitemId;
    }

    public void setMaterialrequestitemId(Integer materialrequestitemId) {
        this.materialrequestitemId = materialrequestitemId;
    }

    public Integer getMaterialrequestId() {
        return materialrequestId;
    }

    public void setMaterialrequestId(Integer materialrequestId) {
        this.materialrequestId = materialrequestId;
    }

    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    

   

    
}
