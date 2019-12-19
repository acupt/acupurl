package com.acupt.surl.persistence.repository.impl;

import com.acupt.surl.persistence.mapper.SurlMapper;
import com.acupt.surl.persistence.model.SurlDO;
import com.acupt.surl.persistence.repository.SurlRepository;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;

/**
 * @author liujie
 */
@Repository
public class SurlRepositoryImpl implements SurlRepository {

    @Resource
    private SurlMapper surlMapper;

    @Override
    public void insert(SurlDO record) {
        surlMapper.insert(record);
    }

    @Override
    public SurlDO getById(String id) {
        return surlMapper.selectByPrimaryKey(id);
    }
}
