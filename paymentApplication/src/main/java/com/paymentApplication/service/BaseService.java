package com.paymentApplication.service;

public interface BaseService<ENTITY, ID> {

    ENTITY findById(ID id);

    ENTITY create(ENTITY entity);

    ENTITY update(ENTITY entity);

    void delete(ID id);
}
