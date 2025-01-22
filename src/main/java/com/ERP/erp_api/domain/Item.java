package com.ERP.erp_api.domain;

import java.sql.Timestamp;

public class Item {

    private Integer itemId;
    private String item_name;
    private java.sql.Timestamp updateAt;
    
    public Item(Integer itemId, String item_name, Timestamp updateAt) {
        this.itemId = itemId;
        this.item_name = item_name;
        this.updateAt = updateAt;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public String getItem_name() {
        return item_name;
    }

    public void setItem_name(String item_name) {
        this.item_name = item_name;
    }

    public java.sql.Timestamp getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(java.sql.Timestamp updateAt) {
        this.updateAt = updateAt;
    } 


}
