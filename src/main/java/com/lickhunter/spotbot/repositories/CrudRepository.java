package com.lickhunter.spotbot.repositories;

import java.io.Serializable;

public interface CrudRepository<DTO, ENTITY, ID extends Serializable> {
    ENTITY save(ENTITY entity);
    ENTITY findOne(ID primaryKey);
    Iterable<ENTITY> findAll();
    int count();
    void delete(ENTITY entity);
    boolean exists(ID primaryKey);
}
