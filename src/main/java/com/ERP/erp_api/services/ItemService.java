package com.ERP.erp_api.services;

import java.util.List;

import com.ERP.erp_api.domain.Item;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface ItemService {

    List<Item> fetchAllItems();
    
    Item fetchItemsById(Integer itemId) throws EtResourceNotFoundException;

    Item addItem(String item_name) throws EtBadRequestException;

    void updateItem( Integer itemId, Item item) throws EtBadRequestException;

    void removeItem(Integer itemId) throws EtResourceNotFoundException;

    
}
