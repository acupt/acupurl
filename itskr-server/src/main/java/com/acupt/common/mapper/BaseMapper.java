package com.acupt.common.mapper;

import tk.mybatis.mapper.common.Mapper;
import tk.mybatis.mapper.common.MySqlMapper;

/**
 * @author liujie
 */
public interface BaseMapper<T> extends Mapper<T>, MySqlMapper<T> {
}
