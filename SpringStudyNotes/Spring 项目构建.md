#Spring 项目构建



##helloworld

1. 新建控制器类文件夹 ***controller***，新建java控制类 `UserController.java`

   1. class前增加两行注解 

      ```java
      @controller
      
      @RequestMapping
      ```

   2. 

2. 新建对象类文件夹 ***entity***，新建java对象类 `User.java`

   1. 构造三个private属性：

      ```
      private String name;
      private int age;
      private String addr;
      ```

   2. 右键-generate 快速生成getter和setter方法，如果容器内有对应的bean会启用自动注解

   3. 设置构造器，

3. 新建服务类文件夹 ***service*** ， 新建java服务接口类 `UserService.java`

   1. 调用UserController.java

4. 构建容器和bean，在/src/main/resource目录下新建"SpringConfig"类，命名为`ApplicationContext.xml` (尽量把.xml文件放在resource资源文件下)

   1. 创建spring的IOC容器对象

   2. 配置user的bean，

   3. 从IOC容器中获取bean实例：

      利用id定位到IOC容器中的bean，利用类型返回IOC容器中的bean，但要求IOC容器中必须只能有一个该类型的bean

      ```
      ApplicationContext act= new ClassPathXmlApplicationContext("ApplicationContext.xml");	
      User user1 = (User)act.getBean("User");
      user1.sayHello();
      ```





通过构造器或工厂方法创建Bean实例，



# Aspectj

1. 添加jar包
2. 添加依赖
3. 基于注解的方式：
   1. 在配置文件中添加配置`<aop:aspectj-autoproxy></aop:aspectj-autoproxy>`
   2. 把横切关注点的代码抽象到切面的类中
      1. 切面首先是一个ioc中的bean，即加入@Component注解
      2. 注解还需要加入@Aspect注解
   3. 在类中声明 通知注解(@Before，@After，@AfterRunning，@AfterThrowing，@Around，)
4. 基于配置文件的配置





### 事务

 



