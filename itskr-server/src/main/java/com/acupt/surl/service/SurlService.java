package com.acupt.surl.service;

import com.acupt.common.AcupResult;
import com.acupt.itskr.domain.constant.CounterEnum;
import com.acupt.itskr.service.IdService;
import com.acupt.surl.domain.form.SurlCreateForm;
import com.acupt.surl.persistence.model.SurlDO;
import com.acupt.surl.persistence.repository.SurlRepository;
import com.acupt.surl.util.SurlUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author liujie
 */
@Service
public class SurlService {

    @Resource
    private SurlRepository surlRepository;

    @Resource
    private IdService idService;

    public AcupResult<SurlDO> create(SurlCreateForm form) {
        long seq = idService.createId(CounterEnum.SURL_SEQ.getName());
        if (seq == 0) {
            return AcupResult.error("资源不足");
        }
        SurlDO surlDO = new SurlDO();
        surlDO.setSeq(seq);
        surlDO.setId(SurlUtil.seq2id(seq));
        surlDO.setTargetUrl(form.getUrl());
        surlDO.setCreatedTime(System.currentTimeMillis());
        surlDO.setInvalidTime(System.currentTimeMillis() + form.getTtl() * 1000);
        surlRepository.insert(surlDO);
        return AcupResult.success(surlDO);
    }

    public AcupResult<SurlDO> getById(String id) {
        return AcupResult.success(surlRepository.getById(id));
    }
}
