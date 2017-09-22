package com.turkeymz.service.impl;

import com.turkeymz.dao.DaoExample;
import com.turkeymz.entity.EntityExample;
import com.turkeymz.service.ServiceExample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * 样例ServiceImpl
 * Created by turkeymz on 2017/8/3.
 */
@Service("serviceExample")
public class ServiceImplExample implements ServiceExample {

    @Autowired
    DaoExample daoExample;

    public EntityExample helloWorld(String id) {

        EntityExample entityExample = daoExample.findOne(Integer.parseInt(id));
        return entityExample;
    }
}

