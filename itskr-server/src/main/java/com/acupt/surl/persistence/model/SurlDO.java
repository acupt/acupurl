package com.acupt.surl.persistence.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "surl")
public class SurlDO {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "seq")
    private Long seq;

    @Column(name = "target_url")
    private String targetUrl;

    @Column(name = "created_time")
    private Long createdTime;

    @Column(name = "invalid_time")
    private Long invalidTime;
}