package com.syt.config;

import com.alibaba.druid.support.http.WebStatFilter;

import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;

@WebFilter(filterName = "statfilter",
        urlPatterns = "/*",
        initParams = {
            @WebInitParam(name = "exclusions",
                    value = "*.js,*.css,*.gif,*.jpg,*.bmp,*.png,*.ico,,/druid/*")
        })//== web.xml <filter></filter>
public class DruidWebStatFilter extends WebStatFilter {



}
