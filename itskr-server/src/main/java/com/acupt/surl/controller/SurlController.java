package com.acupt.surl.controller;

import com.acupt.common.AcupResult;
import com.acupt.surl.domain.form.SurlCreateForm;
import com.acupt.surl.service.SurlService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author liujie
 */
@Controller
@RequestMapping("/surl")
public class SurlController {

    @Value("${surl.domain}")
    private String domain;

    @Resource
    private SurlService surlService;

    @PostMapping(value = "/create")
    @ResponseBody
    public AcupResult<String> create(SurlCreateForm form) {
        return surlService.create(form).transform(d -> domain + "/" + d.getId());
    }
}
