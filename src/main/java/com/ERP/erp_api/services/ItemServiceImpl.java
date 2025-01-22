package com.ERP.erp_api.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.erp_api.domain.Item;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.ItemRepository;

@Service
@Transactional
public class ItemServiceImpl implements ItemService {

    @Autowired
    ItemRepository itemRepository;

    @Override
    public Item addItem(String item_name) throws EtBadRequestException {
      int itemId = itemRepository.create(item_name);
      return itemRepository.findById(itemId);
    }

    @Override
    public List<Item> fetchAllItems() {
        return itemRepository.findAll();
    }

    @Override
    public Item fetchItemsById(Integer itemId) throws EtResourceNotFoundException {
        return itemRepository.findById(itemId);
    }

    @Override
    public void removeItem(Integer itemId) throws EtResourceNotFoundException {
        itemRepository.removeById(itemId);
    }

    @Override
    public void updateItem(Integer itemId, Item item) throws EtBadRequestException {
        itemRepository.update(itemId, item);
        
    }

}
