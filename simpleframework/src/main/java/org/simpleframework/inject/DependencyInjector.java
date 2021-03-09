package org.simpleframework.inject;

import lombok.extern.slf4j.Slf4j;
import org.simpleframework.core.BeanContainer;
import org.simpleframework.inject.annotation.Autowired;
import org.simpleframework.util.ClassUtil;
import org.simpleframework.util.ValidationUtil;

import java.lang.reflect.Field;
import java.util.Set;

@Slf4j
public class DependencyInjector {
    /**
     * Bean容器
     */
    private BeanContainer beanContainer;

    public DependencyInjector(){
        beanContainer = BeanContainer.getInstance();
    }

    /**
     * 执行IOC
     */
    public void doIOC(){
        //1.遍历Bean容器中所有的Class对象
        Set<Class<?>> classes = beanContainer.getClasses();
        if (ValidationUtil.isEmpty(classes)) {
            log.warn("empty classSet in BeanContainer");
            return;
        }
        for (Class<?> clazz : classes) {
            //2.遍历Class对象的所有成员变量
            Field[] fields = clazz.getDeclaredFields();
            if (ValidationUtil.isEmpty(fields)) {
                continue;
            }
            for (Field field : fields) {
                //3.找出被Autowired标记的成员变量
                if (field.isAnnotationPresent(Autowired.class)) {
                    Autowired autowired = field.getAnnotation(Autowired.class);
                    String autowiredValue = autowired.value();
                    //4.获取这些成员变量的类型
                    Class<?> fieldClass = field.getType();
                    //5.获取这些成员变量的类型在容器里对应的实例
                    Object fileValue = getFieldInstance(fieldClass, autowiredValue);
                    if (null == fileValue) {
                        throw new RuntimeException("unable to inject relevant type, target fieldClass is:"+fieldClass.getName() + " autowired:"+autowiredValue);
                    } else {
                        //6.通过反射将对应的成员变量实例注入到成员变量所在类的实例里
                        Object targetBean = beanContainer.getBean(clazz);
                        ClassUtil.setField(field,targetBean,fileValue,true);
                    }
                }
            }
        }
    }

    /**
     * 根据Class对象在beanContainer里获取其实例或者实现类
     * @param fieldClass
     * @param autowiredValue
     * @return
     */
    private Object getFieldInstance(Class<?> fieldClass, String autowiredValue) {
        Object fieldValue = beanContainer.getBean(fieldClass);
        if (null != fieldValue) {
            return fieldValue;
        }
        Class<?> implementedClass = getImplementedClass(fieldClass, autowiredValue);
        if (null != implementedClass) {
            return beanContainer.getBean(implementedClass);
        }
        return null;
    }

    /**
     * 获取接口的实现类
     * @param fieldClass
     * @param autowiredValue
     * @return
     */
    private Class<?> getImplementedClass(Class<?> fieldClass, String autowiredValue) {
        Set<Class<?>> classSet = beanContainer.getClassesBySuper(fieldClass);
        if (!ValidationUtil.isEmpty(classSet)) {
            if (ValidationUtil.isEmpty(autowiredValue)) {
                if (1 == classSet.size()) {
                    return classSet.iterator().next();
                } else {
                    //如果有多于两个实现类且用户未指定其中一个实现类，则抛出异常
                    throw new RuntimeException("multiple implemented classes for " + fieldClass.getName() + " please set @Autowired's value to pick one");
                }
            } else {
                for (Class<?> clazz : classSet) {
                    if (autowiredValue.equals(clazz.getSimpleName())) {
                        return clazz;
                    }
                }
            }
        }
        return null;
    }
}
