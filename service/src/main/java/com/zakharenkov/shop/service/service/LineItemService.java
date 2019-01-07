package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.LineItem;
import com.zakharenkov.shop.database.repository.LineItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.jws.WebService;

@Service
@Transactional
public class LineItemService {

    @Autowired
    private LineItemRepository lineItemRepository;

    public LineItem save(LineItem item) {
        return lineItemRepository.save(item);
    }
}
