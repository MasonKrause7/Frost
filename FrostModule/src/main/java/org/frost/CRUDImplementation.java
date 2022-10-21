package org.frost;

import org.frost.util.datainterfaces.CRUDRepository;

public class CRUDImplementation<T> implements CRUDRepository<T> {


    @Override
    public void save(T entity) {
        System.out.println("saving entity of "+ entity );
        System.out.println("entity saved");
    }


}
