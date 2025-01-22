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

import com.ERP.erp_api.domain.MaterialRequestItem;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.services.MaterialRequestItemService;

import jakarta.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/materialrequestitem")
public class MaterialRequestItemResource {

    @Autowired
    MaterialRequestItemService materialRequestItemService;

    @GetMapping("")
    public ResponseEntity<List<MaterialRequestItem>> getAllMaterialRequest(HttpServletRequest request) {
        List<MaterialRequestItem> materialRequestItem = materialRequestItemService.fetchAllMaterialRequestItems();
        return new ResponseEntity<>(materialRequestItem, HttpStatus.OK);
    }

    // @GetMapping("/{materialrequestId}")
    // public ResponseEntity<MaterialRequestItem>
    // getMaterialRequestById(HttpServletRequest request,
    // @PathVariable("materialrequestId") Integer materialrequestId){
    // MaterialRequestItem materialRequestItem =
    // materialRequestItemService.fetchMaterialRequestItemsById(materialrequestId);
    // return new ResponseEntity<>(materialRequestItem,HttpStatus.OK);
    // }

    @GetMapping("/{materialrequestId}")
    public ResponseEntity<List<MaterialRequestItem>> getMaterialRequestItemsByRequestId(
            @PathVariable("materialrequestId") Integer materialrequestId) {
        List<MaterialRequestItem> items = materialRequestItemService.fetchMaterialRequestItemsById(materialrequestId);
        return ResponseEntity.ok(items);
    }

    @PostMapping("")
    public ResponseEntity<List<Integer>> addMaterialRequestItems(
            @RequestBody List<Map<String, Object>> materialRequestItems) {
        try {
            List<Integer> createdIds = materialRequestItemService.create(materialRequestItems);
            return new ResponseEntity<>(createdIds, HttpStatus.CREATED);
        } catch (EtBadRequestException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }


    @PutMapping("/bulk-update")
    public ResponseEntity<Map<String, Boolean>> updateMultipleMaterials(
            HttpServletRequest request,
            @RequestBody List<MaterialRequestItem> materialRequestItems) {
        materialRequestItemService.updateMultipleMaterialRequests(materialRequestItems);
        Map<String, Boolean> response = new HashMap<>();
        response.put("Success", true);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @DeleteMapping("/{materialrequestitemId}")
    public ResponseEntity<Map<String, Boolean>> removeMaterial(HttpServletRequest request,
            @PathVariable("materialrequestitemId") Integer materialrequestitemId) {
        materialRequestItemService.removeMaterialRequest(materialrequestitemId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map, HttpStatus.OK);
    }
}
