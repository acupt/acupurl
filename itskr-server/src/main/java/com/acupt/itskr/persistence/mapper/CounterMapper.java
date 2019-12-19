package com.acupt.itskr.persistence.mapper;

import com.acupt.common.mapper.BaseMapper;
import com.acupt.itskr.persistence.model.CounterDO;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CounterMapper extends BaseMapper<CounterDO> {
}
