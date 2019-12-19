package com.acupt.itskr.persistence.repository;

import com.acupt.itskr.persistence.model.CounterDO;

/**
 * @author liujie
 */
public interface CounterRepository {

    void insert(CounterDO record);

    CounterDO getByName(String name);

    long getAdd(String name, long add);
}
