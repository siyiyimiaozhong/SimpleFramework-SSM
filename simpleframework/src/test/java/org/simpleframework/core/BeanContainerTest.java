package org.simpleframework.core;

import com.siyi.controller.superadmin.HeadLineOperationController;
import com.siyi.service.combine.HeadLineShopCategoryCombineService;
import com.siyi.service.combine.impl.HeadLineShopCategoryCombineServiceImpl;
import org.junit.jupiter.api.*;
import org.simpleframework.core.annotation.Service;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class BeanContainerTest {
    private static BeanContainer beanContainer;

    @BeforeAll
    static void init(){
        beanContainer = BeanContainer.getInstance();
    }

    @Order(1)
    @Test
    public void loadBeansTest(){
        Assertions.assertEquals(false, beanContainer.isLoaded());
        beanContainer.loadBeans("com.siyi");
        Assertions.assertEquals(2, beanContainer.size());
        Assertions.assertEquals(true, beanContainer.isLoaded());
    }

    @Order(2)
    @Test
    public void getBeanTest(){
        HeadLineShopCategoryCombineServiceImpl headLineShopCategoryCombineService = (HeadLineShopCategoryCombineServiceImpl)beanContainer.getBean(HeadLineShopCategoryCombineServiceImpl.class);
        Assertions.assertEquals(true, headLineShopCategoryCombineService instanceof HeadLineShopCategoryCombineServiceImpl);
        HeadLineOperationController controller = (HeadLineOperationController) beanContainer.getBean(HeadLineOperationController.class);
        Assertions.assertEquals(null, controller);
    }

    @Order(3)
    @Test
    public void getClassesByAnnotationTest(){
        Assertions.assertEquals(true,beanContainer.isLoaded());
        Assertions.assertEquals(1,beanContainer.getClassesByAnnotation(Service.class).size());
    }

    @Order(4)
    @Test
    public void getClassesBySuperTest(){
        Assertions.assertEquals(true, beanContainer.isLoaded());
        Assertions.assertEquals(true, beanContainer.getClassesBySuper(HeadLineShopCategoryCombineService.class).contains(HeadLineShopCategoryCombineServiceImpl.class));
    }
}
