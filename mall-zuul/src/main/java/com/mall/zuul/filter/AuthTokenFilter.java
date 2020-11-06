package com.mall.zuul.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.mall.common.vo.MallResult;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;
import lombok.SneakyThrows;
import org.apache.commons.lang.StringUtils;
import org.springframework.http.HttpHeaders;

import javax.servlet.http.HttpServletRequest;

public class AuthTokenFilter extends ZuulFilter {
    @Override
    public String filterType() {
        return "pre";
    }

    @Override
    public int filterOrder() {
        return 0;
    }

    @Override
    public boolean shouldFilter() {
        return false;
    }

    @SneakyThrows
    @Override
    public Object run() throws ZuulException {
        RequestContext context = RequestContext.getCurrentContext();
        HttpServletRequest request = context.getRequest();

        String token = request.getHeader(HttpHeaders.AUTHORIZATION);
        if (StringUtils.isEmpty(token)) {
            context.setSendZuulResponse(false);
            context.setResponseStatusCode(401);
            ObjectMapper objectMapper = new ObjectMapper();
            context.setResponseBody(objectMapper.writeValueAsString(MallResult.fail("401","未携带请求TOKEN")));

            context.getResponse().setCharacterEncoding("UTF-8");
            return null;
        }

        // 将请求token传递下去
        context.addZuulRequestHeader(HttpHeaders.AUTHORIZATION,token);
        return null;
    }
}
