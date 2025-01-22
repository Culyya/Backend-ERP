package com.ERP.erp_api.domain;

public class Departement {

    private Integer departementId;
    private String  departement_name;
    
    public Departement(Integer departementId, String departement_name) {
        this.departementId = departementId;
        this.departement_name = departement_name;
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


}
