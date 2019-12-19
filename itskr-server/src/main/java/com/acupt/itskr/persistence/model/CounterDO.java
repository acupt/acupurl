package com.acupt.itskr.persistence.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "counter")
public class CounterDO {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "count")
    private Long count;
}