package com.lickhunter.spotbot.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.Serializable;

public interface PagingAndSortingRepository<T, ID extends Serializable, DTO> extends CrudRepository<DTO, T, ID> {
    Iterable<T> findAll(Sort sort);
    Page<T> findAll(Pageable pageable);
}
