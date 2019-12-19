package com.acupt.itskr.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

import static com.acupt.common.constant.Constants.PAGE_HOME;

/**
 * @author liujie
 */
@Controller
public class IndexController {


    @GetMapping("/")
    public String index() {
        return PAGE_HOME;
    }

    @RequestMapping(value = "/*.html", method = RequestMethod.GET)
    public ModelAndView html(HttpServletRequest request) {
        String uri = request.getRequestURI();
        uri = uri.substring(1, uri.length() - 5);
        ModelAndView view = new ModelAndView(uri);
        Enumeration<String> name = request.getParameterNames();
        while (name.hasMoreElements()) {
            String k = name.nextElement();
            view.addObject(k, request.getParameter(k));
        }
        return view;
    }

    @RequestMapping(value = "/*/*.html", method = RequestMethod.GET)
    public ModelAndView html2(HttpServletRequest request) {
        return html(request);
    }
}
