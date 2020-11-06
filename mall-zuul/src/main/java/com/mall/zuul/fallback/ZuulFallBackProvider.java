package com.mall.zuul.fallback;

import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.BlockResponse;
import com.alibaba.csp.sentinel.adapter.gateway.zuul.fallback.ZuulBlockFallbackProvider;
import com.alibaba.csp.sentinel.slots.block.BlockException;

public class ZuulFallBackProvider implements ZuulBlockFallbackProvider {

    @Override
    public String getRoute() {
        return null;
    }

    @Override
    public BlockResponse fallbackResponse(String route, Throwable throwable) {
        if (throwable instanceof BlockException) {
            return new BlockResponse(429, "服务器请求超时", route);
        } else {
            return new BlockResponse(500, "系统异常", route);
        }
    }
}
