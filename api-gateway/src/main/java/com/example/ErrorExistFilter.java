package com.example;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.springframework.cloud.netflix.zuul.filters.post.SendErrorFilter;
import org.springframework.stereotype.Component;

/**
 * @ClassName: ErrorExtFilter
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2018/11/21 16:24
 **/
@Component
public class ErrorExistFilter extends SendErrorFilter {

    @Override
    public String filterType() {
//        return super.filterType();
        return "error";
    }

    @Override
    public int filterOrder() {
//        return super.filterOrder();
        return 30;//// 大于ErrorFilter的值,在ErrorFilter之后执行
    }

    @Override
    public boolean shouldFilter() {
//        return super.shouldFilter();
        //需判断仅处理来自post的过滤器引起的异常
        RequestContext ctx = RequestContext.getCurrentContext();
        ZuulFilter failedFilter = (ZuulFilter) ctx.get("failed.filter");
        if(failedFilter != null && failedFilter.filterType().equals("post")) {
            return true;
        }
        return false;
     }

    @Override
    public Object run() {
        return super.run();
    }
}
