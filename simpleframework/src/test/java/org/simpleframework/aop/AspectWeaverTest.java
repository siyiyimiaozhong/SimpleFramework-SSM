package org.simpleframework.aop;

import com.siyi.controller.frontend.MainPageController;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.DependencyInjector;

public class AspectWeaverTest {

    @DisplayName("织入通用逻辑测试：doAop")
    @Test
    public void doAopTest(){
        BeanContainer beanContainer = BeanContainer.getInstance();
        beanContainer.loadBeans("com.siyi");
        new AspectWeaver().doAop();
        new DependencyInjector().doIOC();
        MainPageController mainPageController = (MainPageController)beanContainer.getBean(MainPageController.class);
        mainPageController.fun();
    }
}
