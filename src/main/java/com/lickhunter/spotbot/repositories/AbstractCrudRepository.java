package com.lickhunter.spotbot.repositories;


import lombok.RequiredArgsConstructor;
import org.jooq.DSLContext;
import org.jooq.Table;
import org.jooq.impl.UpdatableRecordImpl;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.Objects;


@RequiredArgsConstructor
@Transactional(transactionManager = "transactionManager")
public abstract class AbstractCrudRepository<DTO, ENTITY extends UpdatableRecordImpl<ENTITY>>
        implements CrudRepository<DTO, ENTITY, Serializable> {

    protected final DSLContext dsl;
    protected Table<ENTITY> table;

    @Override
    public abstract ENTITY save(ENTITY entity);

    @Override
    public ENTITY findOne(Serializable primaryKey) {
        return dsl.selectFrom(table)
                .where(table.getPrimaryKey().equals(primaryKey))
                .fetchOne();
    }

    @Override
    public Iterable<ENTITY> findAll() {
        return dsl.selectFrom(table)
                .fetch();
    }

    @Override
    public int count() {
        return dsl.fetchCount(table);
    }

    @Override
    public void delete(ENTITY entity) {
        dsl.delete(table)
            .where(Objects.equals(table.getPrimaryKey(), entity))
            .execute();
    }

    @Override
    public boolean exists(Serializable primaryKey) {
        return dsl.selectFrom(table)
                .where(Objects.equals(table.getPrimaryKey(), primaryKey))
                .fetch().isNotEmpty();
    }
}
