package com.ERP.erp_api.resources;



import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.ERP.erp_api.domain.MaterialRequest;
import com.ERP.erp_api.services.MaterialRequestService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/materialrequest")
public class MaterialRequestResource {
    
    @Autowired
    MaterialRequestService materialRequestService;

    @GetMapping("")
    public ResponseEntity<List<MaterialRequest>>  getAllMaterialRequest(HttpServletRequest request){
        List<MaterialRequest> materialRequests = materialRequestService.fetchAllMaterialRequest();
        return new ResponseEntity<>(materialRequests,HttpStatus.OK);
    }

    @GetMapping("/{materialrequestId}")
    public ResponseEntity<MaterialRequest> getMaterialRequestById(HttpServletRequest request, @PathVariable("materialrequestId") Integer materialrequestId){
        MaterialRequest materialRequest = materialRequestService.fetchMaterialRequestById(materialrequestId);
        return new ResponseEntity<>(materialRequest,HttpStatus.OK);
    }


    @PostMapping("")
    public ResponseEntity<MaterialRequest> addMaterial(HttpServletRequest request, @RequestBody Map<String, Object> materialmap){
        Integer createdBy = (Integer) materialmap.get("created_by");
        MaterialRequest materialRequest = materialRequestService.addMaterialRequest(createdBy);
        return  new ResponseEntity<>(materialRequest,HttpStatus.CREATED);
    }

    @PutMapping("/{materialrequestId}")
    public ResponseEntity<Map<String, Boolean>> updateMaterial(HttpServletRequest request, @PathVariable("materialrequestId") Integer materialrequestId, @RequestBody MaterialRequest materialRequest) {
        materialRequestService.updateStatus(materialrequestId, materialRequest);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @PutMapping("/remove/{materialrequestId}")
    public ResponseEntity<Map<String, Boolean>> removeMaterial(HttpServletRequest request, @PathVariable("materialrequestId") Integer materialrequestId,@RequestBody MaterialRequest materialRequest){
        materialRequestService.removeMaterialRequest(materialrequestId,materialRequest);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

}
