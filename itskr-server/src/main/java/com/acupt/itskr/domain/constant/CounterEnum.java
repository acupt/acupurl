package com.acupt.itskr.domain.constant;

import lombok.Getter;

/**
 * @author liujie
 */
@Getter
public enum CounterEnum {

    // 短链接自增ID
    SURL_SEQ("surl_seq", 300000L);

    private String name;

    private long initValue;

    CounterEnum(String name, long initValue) {
        this.name = name;
        this.initValue = initValue;
    }
}
