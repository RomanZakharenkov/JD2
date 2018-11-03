package dao;

import model.Storage;

public class StorageDaoImpl extends BaseDaoImpl<Long, Storage> implements StorageDao {

    private static final StorageDao INSTANCE = new StorageDaoImpl(Storage.class);

    // TODO: реализация кастомных методов на склад

    public StorageDaoImpl(Class<Storage> clazz) {
        super(clazz);
    }

    public static StorageDao getInstance() {
        return INSTANCE;
    }
}
