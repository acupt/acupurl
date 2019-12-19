package com.acupt.surl.persistence.repository;

import com.acupt.surl.persistence.model.SurlDO;

/**
 * @author liujie
 */
public interface SurlRepository {

    void insert(SurlDO record);

    SurlDO getById(String id);
}
