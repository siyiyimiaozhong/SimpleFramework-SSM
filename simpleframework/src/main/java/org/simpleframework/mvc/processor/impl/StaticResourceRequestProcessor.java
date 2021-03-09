package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * 静态资源请求处理，包括但不限于图片,css,以及js文件等 DefaultServlet
 */
@Slf4j
public class StaticResourceRequestProcessor implements RequestProcessor {
    public static final String DEFAULT_TOMCAT_SERVLET = "default";
    public static final String STATIC_RESOURCE_PREFIX = "/static/";
    //tomcat默认请求派发器RequestDispatcher的名称 defaultDispatcher
    private RequestDispatcher requestDispatcher;

    public StaticResourceRequestProcessor(ServletContext servletContext){
        this.requestDispatcher=servletContext.getNamedDispatcher(DEFAULT_TOMCAT_SERVLET);
        if (this.requestDispatcher==null){
            throw new RuntimeException("there is no default tomcat servlet");
        }
        log.info("the default servlet for static resource is {}", DEFAULT_TOMCAT_SERVLET);
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        //1.通过请求路径判断是否是请求的静态资源(webapp/static)，如果是则转发给default servlet处理
        if (isStaticResource(requestProcessorChain.getRequestPath())){
            //2.如果是静态资源，则将请求转发给default servlet处理
            requestDispatcher.forward(requestProcessorChain.getRequest(),requestProcessorChain.getResponse());
            return false;
        }
        return true;
    }

    private boolean isStaticResource(String requestPath) {
        return requestPath.startsWith(STATIC_RESOURCE_PREFIX);
    }
}
