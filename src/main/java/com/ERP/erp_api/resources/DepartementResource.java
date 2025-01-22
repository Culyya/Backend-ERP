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

import com.ERP.erp_api.domain.Departement;
import com.ERP.erp_api.services.DepartementService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/departement")
public class DepartementResource {

    @Autowired
    DepartementService departementService;

    @GetMapping("")
    public ResponseEntity<List<Departement>>  getAllDepartement(HttpServletRequest request){
        List<Departement> item = departementService.fetchAllDepartements();
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @GetMapping("/{departementId}")
    public ResponseEntity<Departement> getDepartementById(HttpServletRequest request, @PathVariable("departement_id") Integer departementId){
        Departement departement = departementService.fetchDepartementById(departementId);
        return new ResponseEntity<>(departement,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Departement> addDepartement(HttpServletRequest request, @RequestBody Map<String, Object> departementmap){
        String departement_name = (String) departementmap.get("departement_name");
        Departement departement = departementService.addDepartement(departement_name);
        return  new ResponseEntity<>(departement,HttpStatus.CREATED);
    }

    @PutMapping("/{departementId}")
    public ResponseEntity<Map<String, Boolean>> updateItem(HttpServletRequest request, @PathVariable("itemId") Integer departementId, @RequestBody Departement departement) {
        departementService.updateDepartement(departementId, departement);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{departementId}")
    public ResponseEntity<Map<String, Boolean>> removeItem(HttpServletRequest request, @PathVariable("departementId") Integer departementId){
        departementService.removeDepartement(departementId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
  

}
