package com.ERP.erp_api.repositories;

import java.util.List;

import com.ERP.erp_api.domain.Item;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;

public interface ItemRepository {

    List<Item> findAll() throws EtResourceNotFoundException;

    Item findById(Integer itemId) throws EtResourceNotFoundException;

    Integer create(String item_name) throws EtBadRequestException;

    void update( Integer itemId, Item item) throws EtBadRequestException;

    void removeById(Integer itemId)  throws EtBadRequestException;

}
