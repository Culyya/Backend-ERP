package com.ERP.erp_api.services;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ERP.erp_api.domain.MaterialRequestItem;
import com.ERP.erp_api.exceptions.EtBadRequestException;
import com.ERP.erp_api.exceptions.EtResourceNotFoundException;
import com.ERP.erp_api.repositories.MaterialRequestItemRepository;

@Service
@Transactional
public class MaterialRequestItemServiceImpl implements MaterialRequestItemService {

    @Autowired
    MaterialRequestItemRepository materialRequestItemRepository;

    @Override
    public List<Integer> create(List<Map<String, Object>> materialRequestItems) throws EtBadRequestException {
        return materialRequestItemRepository.create(materialRequestItems);
    }

    @Override
    public List<MaterialRequestItem> fetchAllMaterialRequestItems() {
        return materialRequestItemRepository.findAll();
    }

    @Override
    public List<MaterialRequestItem> fetchMaterialRequestItemsById(Integer materialrequestId)
            throws EtResourceNotFoundException {
        List<MaterialRequestItem> items = materialRequestItemRepository.findById(materialrequestId);
        if (items.isEmpty()) {
            throw new EtResourceNotFoundException("No material request items found for ID " + materialrequestId);
        }
        return items;
    }

    @Override
    public void removeMaterialRequest(Integer materialrequestitemId) throws EtResourceNotFoundException {
        materialRequestItemRepository.removeById(materialrequestitemId);
    }

    @Override
    public void updateMultipleMaterialRequests(List<MaterialRequestItem> materialRequestItems)
            throws EtBadRequestException {
        if (materialRequestItems == null || materialRequestItems.isEmpty()) {
            throw new EtBadRequestException("No items to update");
        }
        materialRequestItemRepository.updateBatch(materialRequestItems);
    }
}
