package com.acupt.surl.controller;

import com.acupt.common.AcupResult;
import com.acupt.surl.persistence.model.SurlDO;
import com.acupt.surl.service.SurlService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static com.acupt.common.constant.Constants.PAGE_HOME;

/**
 * @author liujie
 */
@Controller
public class RedirectController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Map<String, String> map = new ConcurrentHashMap<>();

    @Resource
    private SurlService surlService;

    @GetMapping("/{id:[0-9A-Za-z]+}")
    public String redirect(@PathVariable("id") String id, HttpServletResponse response) {
        String url = map.get(id);
        if (url == null) {
            AcupResult<SurlDO> result = surlService.getById(id);
            if (!result.isSuccess() || result.getData() == null) {
                logger.warn("getById {}", result);
                return PAGE_HOME;
            }
            url = result.getData().getTargetUrl();
            map.put(id, url);
        }
        try {
            response.sendRedirect(url);
        } catch (IOException e) {
            logger.error("sendRedirect {} {}", id, url, e);
            return PAGE_HOME;
        }
        return null;
    }
}
