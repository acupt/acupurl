package com.acupt.surl.domain.form;

import lombok.Data;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

/**
 * @author liujie
 */
@Data
public class SurlCreateForm {

    @Size(max = 255)
    @NotBlank
    private String url;

    @Min(0)
    @Max(60 * 60 * 24 * 5)
    private int ttl;//有效时间，秒
}
