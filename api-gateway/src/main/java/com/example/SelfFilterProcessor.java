package com.example;

import com.netflix.zuul.FilterProcessor;
import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import com.netflix.zuul.exception.ZuulException;

/**
 * @ClassName: SelfFilterProcessor
 * @Description: 扩展processZuulFilter(ZuulFilter filter)方法
 * 参考来源：http://www.spring4all.com/article/311
 * @Author Comsys-xianjiao.luo
 * @Date 2018/11/22 11:31
 **/
public class SelfFilterProcessor extends FilterProcessor {

    @Override
    public Object processZuulFilter(ZuulFilter filter) throws ZuulException {
        try {
            return super.processZuulFilter(filter);
        } catch (ZuulException e) {
            RequestContext ctx = RequestContext.getCurrentContext();
            ctx.set("failed.filter", filter);
            throw e;
        }
    }
}
