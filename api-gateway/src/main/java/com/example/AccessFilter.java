package com.example;

import com.netflix.zuul.ZuulFilter;
import com.netflix.zuul.context.RequestContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
/*
filterType：过滤器的类型，它决定过滤器在请求的哪个生命周期中执行。这里定义为pre，代表会在请求被路由之前执行。
filterOrder：过滤器的执行顺序。当请求在一个阶段中存在多个过滤器时，需要根据该方法返回的值来依次执行。
shouldFilter：判断该过滤器是否需要被执行。这里我们直接返回了true，因此该过滤器对所有请求都会生效。实际运用中我们可以利用该函数来指定过滤器的有效范围。
run：过滤器的具体逻辑。这里我们通过ctx.setSendZuulResponse(false)令zuul过滤该请求，不对其进行路由，然后通过ctx.setResponseStatusCode(401)设置了其返回的错误码，当然我们也可以进一步优化我们的返回，比如，通过ctx.setResponseBody(body)对返回body内容进行编辑等
 */
/**
 * @ClassName: AccessFilter
 * @Description: Zuul过滤器
 * @Author Comsys-xianjiao.luo
 * @Date 2018/11/20 14:36
 **/
public class AccessFilter extends ZuulFilter {

    private static Logger logger = LoggerFactory.getLogger(AccessFilter.class);
    //filterType:
    //    pre：可以在请求被路由之前调用。
    //    routing：在路由请求时候被调用。
    //    post：在routing和error过滤器之后被调用。
    //    error：处理请求时发生错误时被调用。
    @Override
    public String filterType() {//过滤类型
        return "pre";
    }

    @Override
    public int filterOrder() {//执行顺序
        return 0;
    }

    @Override
    public boolean shouldFilter() {//执行条件,判断该过滤器是否要执行。我们可以通过此方法来指定过滤器的有效范围
//        return false;//false表明不过滤(不走过滤器)
        return true;
    }

    @Override
    public Object run() {//具体操作,可以实现自定义的过滤逻辑，来确定是否要拦截当前的请求，不对其进行后续的路由，或是在请求路由返回结果之后，对处理结果做一些加工等
        RequestContext requestContext = RequestContext.getCurrentContext();
        HttpServletRequest request = requestContext.getRequest();
        logger.info("method：{} ，url：{} ",request.getMethod(),request.getRequestURL().toString());
        //抛出异常用来测试ErrorFilter
        try {
            throwException();
        } catch (Exception e) {
            requestContext.set("error.status_code", HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            requestContext.set("error.exception", e);
        }
       /* String token = request.getParameter("token");
        if(token == null) {
            logger.warn("token is null");
            requestContext.setSendZuulResponse(false);
            requestContext.setResponseStatusCode(401);
            return null;
        }
        logger.info("token is ok");*/
        return null;
    }
    private void throwException() {
        throw new RuntimeException("运行时异常");
    }
}
