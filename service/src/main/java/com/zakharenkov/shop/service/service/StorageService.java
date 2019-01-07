package com.zakharenkov.shop.service.service;

import com.zakharenkov.shop.database.model.Storage;
import com.zakharenkov.shop.database.repository.StorageRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class StorageService {

    @Autowired
    private StorageRepository storageRepository;

    public Storage save(Storage storage) {
        return storageRepository.save(storage);
    }
}
