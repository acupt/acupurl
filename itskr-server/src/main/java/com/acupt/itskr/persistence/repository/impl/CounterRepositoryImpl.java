package com.acupt.itskr.persistence.repository.impl;

import com.acupt.itskr.persistence.mapper.CounterMapper;
import com.acupt.itskr.persistence.model.CounterDO;
import com.acupt.itskr.persistence.model.CounterDOExample;
import com.acupt.itskr.persistence.repository.CounterRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author liujie
 */
@Slf4j
@Repository
public class CounterRepositoryImpl implements CounterRepository {

    @Resource
    private CounterMapper counterMapper;

    @Override
    public void insert(CounterDO record) {
        counterMapper.insert(record);
    }

    @Override
    public CounterDO getByName(String name) {
        return counterMapper.selectByPrimaryKey(name);
    }

    @Override
    public long getAdd(String name, long add) {
        if (add <= 0) {
            throw new IllegalArgumentException("add > 0, ok?");
        }
        while (true) {
            CounterDO record = getByName(name);
            if (record == null) {
                throw new IllegalArgumentException(name + " not exist");
            }
            long newCount = record.getCount() + add;
            CounterDOExample example = new CounterDOExample();
            example.createCriteria().andNameEqualTo(name).andCountEqualTo(record.getCount());
            CounterDO update = new CounterDO();
            update.setCount(newCount);
            if (counterMapper.updateByExampleSelective(update, example) > 0) {
                return record.getCount();
            }
            log.info("counter cas failed {} : {} + {} -> {}", name, record.getCount(), add, newCount);
        }
    }
}
