package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;

/**
 * jsp资源请求处理
 */
@Slf4j
public class JspRequestProcessor implements RequestProcessor {
    //jsp请求的RequestDispatcher的名称
    private static final String JSP_SERVLET = "jsp";
    //jsp请求资源路径前缀
    private static final String JSP_RESOURCE_PREFIX = "/templates/";
    //jsp的RequestDispatcher，处理jsp资源
    private RequestDispatcher jspServlet;

    public JspRequestProcessor(ServletContext servletContext) {
        this.jspServlet = servletContext.getNamedDispatcher(JSP_SERVLET);
        if (null == this.jspServlet){
            throw new RuntimeException("there is no jsp servlet");
        }
    }

    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        if (isJspResource(requestProcessorChain.getRequestPath())) {
            this.jspServlet.forward(requestProcessorChain.getRequest(), requestProcessorChain.getResponse());
            return false;
        }
        return true;
    }

    /**
     * 判断是否是jsp资源
     * @param url
     * @return
     */
    private boolean isJspResource(String url) {
        return url.startsWith(JSP_RESOURCE_PREFIX);
    }
}
