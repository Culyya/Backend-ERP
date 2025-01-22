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

import com.ERP.erp_api.domain.Item;
import com.ERP.erp_api.services.ItemService;

import jakarta.servlet.http.HttpServletRequest;



@RestController
@RequestMapping("/api/item")
public class ItemResource {

    @Autowired
    ItemService itemService;

    @GetMapping("")
    public ResponseEntity<List<Item>>  getAllItem(HttpServletRequest request){
        List<Item> item = itemService.fetchAllItems();
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @GetMapping("/{itemId}")
    public ResponseEntity<Item> getItemById(HttpServletRequest request, @PathVariable("itemId") Integer itemId){
        Item item = itemService.fetchItemsById(itemId);
        return new ResponseEntity<>(item,HttpStatus.OK);
    }

    @PostMapping("")
    public ResponseEntity<Item> addItem(HttpServletRequest request, @RequestBody Map<String, Object> itemmap){
        String item_name = (String) itemmap.get("item_name");
        Item item = itemService.addItem(item_name);
        return  new ResponseEntity<>(item,HttpStatus.CREATED);
    }

    @PutMapping("/{itemId}")
    public ResponseEntity<Map<String, Boolean>> updateItem(HttpServletRequest request, @PathVariable("itemId") Integer itemId, @RequestBody Item item) {
        itemService.updateItem(itemId, item);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Success", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }

    @DeleteMapping("/{itemId}")
    public ResponseEntity<Map<String, Boolean>> removeItem(HttpServletRequest request, @PathVariable("itemId") Integer itemId){
        itemService.removeItem(itemId);
        Map<String, Boolean> map = new HashMap<>();
        map.put("Succes", true);
        return new ResponseEntity<>(map,HttpStatus.OK);
    }
  
}
