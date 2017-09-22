package com.turkeymz.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

/**
 * Created by turkeymz on 2017/8/3.
 */
@Entity
@Data
public class EntityExample {

    @Id
    @GeneratedValue
    private int id;

    private String name;
}
