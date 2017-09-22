package com.turkeymz.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.turkeymz.entity.EntityExample;

/**
 * Created by turkeymz on 2017/8/3.
 */
public interface DaoExample extends JpaRepository<EntityExample,Integer>{

    @Query(value = "select * from entity_example where name=?1",nativeQuery = true)
    EntityExample findMineSql(String name);
}
