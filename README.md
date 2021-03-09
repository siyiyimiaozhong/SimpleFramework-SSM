# SimpleFramework-SSM

自定义简易框架，实现spring核心思想IOC和AOP。在AOP中引入AspectJ实现方法级别增强。并实现springMVC基本思想。

# 项目架构说明

* src/main/java/com/siyi 目录下的类都是模拟的业务逻辑，编写的Dao层，Service层，Controller层模拟类。
* src/main/java/org/simpleframework 目录下为框架代码。
  * /core 目录下存放的BeanContainer类为框架的IoC容器，也是框架的核心入口。
    * /core/annotation 目录下主要存放@Component,@Controller,@Service,@Repository注解
  * /inject 目录下为框架IoC模块。DependencyInjector类实现IOC。
    * /inject/annotation 目录下存放@Autowired注解。
  * /aop 目录下主要存放AOP（切面编程相关逻辑）
    * /aop/annotation 主要存放@Aspect,@Order注解，用于标识切面类以及优先级
    * /aop/aspect 定义切面模板方法，以及切面信息类
    * AspectListExecutor切面队列，用来执行横切逻辑，织入
    * AspectWeaver 将横切逻辑织入到被代理的对象中，利用AspectJ
    * PointcutLocator 解析Aspect表达式并且定位被织入的目标
    * ProxyCreator 用户创建代理对象
  * /mvc 目录下主要存放实现SpringMVC思想相关代码
    * /mvc/annotation 存放@RequestMapping,@RequestParam,@ResponseBody定义
    * /mvc/processor 存放相关请求处理器，包含前置处理器，静态资源处理器，jsp处理器，controller请求处理器。
    * /mvc/reder 存放相关渲染器，包含默认渲染器，异常渲染器，json渲染器，资源未找到渲染器，页面渲染器。
    * /mvc/type 用于存放相关pojo
    * DispatcherServlet 所有请求接受类
    * RequestProcessorChain 以责任链的模式执行注册的请求处理器，委派给特定的Render实例对处理后的结果进行渲染。

* src/main/resource 配置资源存放路径
* src/main/webapp 页面相关存放路径
  * /webapp/static 静态资源存放路径
  * /webapp/templates jsp页面存放路径
* src/test/java/org/simpleframework 测试案例存放路径
