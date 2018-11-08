package com.zakharenkov.shop.dao;

import com.zakharenkov.shop.model.Storage;

public class StorageDaoImpl extends BaseDaoImpl<Long, Storage> implements StorageDao {

    private static final StorageDao INSTANCE = new StorageDaoImpl();

    private StorageDaoImpl() {
        super(Storage.class);
    }

    public static StorageDao getInstance() {
        return INSTANCE;
    }
}
