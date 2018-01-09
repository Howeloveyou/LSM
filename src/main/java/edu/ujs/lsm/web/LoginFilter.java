package edu.ujs.lsm.web;


import com.alibaba.druid.util.Base64;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * @Description: TODO
 * @author Howe
 * @date 2017-12-28
 * @version V1.0
 */
@ServletComponentScan
@WebFilter(filterName="LoginFilter",urlPatterns="/*")
public class LoginFilter implements Filter {

    public List<String> excludeUrlList=new ArrayList<>();

    private final Logger logger = LoggerFactory.getLogger(LoginFilter.class);


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        String excludeUrl=filterConfig.getInitParameter("excludeUrl");
        if(!StringUtils.isEmpty(excludeUrl)){
            excludeUrlList.addAll(Arrays.asList(excludeUrl.split(",")));
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        String url = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if (url.startsWith("/") && url.length() > 1) {
            url = url.substring(1);
        }
        if(isInclude(url)){
            chain.doFilter(httpRequest, httpResponse);
            return;
        }


    }

    @Override
    public void destroy() {

    }

    private boolean isInclude(String url) {
        for(String eurl:excludeUrlList){
            if(url.matches(eurl)){
                return true;
            }
        }
        return false;
    }

    protected String getCookieValue(String cookieKey) {
        return getCookieValue(cookieKey, false);
    }

    /**
     * 获取cookie值
     *
     * @author yushigui<br>
     * @taskId <br>
     * @param name
     * @param flag
     * @return <br>
     */
    protected String getCookieValue(String name, boolean flag) {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        Cookie[] cookies = request.getCookies();
        if (cookies != null && cookies.length > 0) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals(name)) {
                    String value = cookie.getValue();
                    if (flag) {
                        value = value.replaceAll("(?i)%3D", "=");
                        value = new String(Base64.base64ToByteArray(value), Charset.forName("utf-8"));
                    }
                    return value;
                }
            }
        }
        return null;
    }
}
