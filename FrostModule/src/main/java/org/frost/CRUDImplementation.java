package org.frost;

import org.frost.util.datainterfaces.CRUDRepository;

public class CRUDImplementation<T> implements CRUDRepository<T> {

    @Override
    public void save(T entity) {
        System.out.println("opening connection");
        System.out.println("saving entity");
        System.out.println("entity saved");
    }


}
