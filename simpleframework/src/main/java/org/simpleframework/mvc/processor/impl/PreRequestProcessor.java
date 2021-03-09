package org.simpleframework.mvc.processor.impl;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.mvc.RequestProcessorChain;
import org.simpleframework.mvc.processor.RequestProcessor;

/**
 * 请求预处理，包括编码以及路径处理
 */
@Slf4j
public class PreRequestProcessor implements RequestProcessor {
    @Override
    public boolean process(RequestProcessorChain requestProcessorChain) throws Exception {
        // 设置请求编码，将其统一设置成utf-8
        requestProcessorChain.getRequest().setCharacterEncoding("UTF-8");

        // 将请求路径的末尾/删除，为后续匹配controller做准备
        String requestPath=requestProcessorChain.getRequestPath();
        if (requestPath.length()>1&&requestPath.endsWith("/")){
            requestProcessorChain.setRequestPath(requestPath.substring(0,requestPath.length()-1));
        }
        log.info("preprocess request {} {}",requestProcessorChain.getRequestMethod(),requestProcessorChain.getRequestPath());
        return true;
    }
}
