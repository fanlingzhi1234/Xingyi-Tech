#Spring 学习笔记

##优点

- 低侵入式设计，代码的污染极低。
- 独立于各种应用服务器，基于Spring框架的应用，可以真正实现Write Once，Run Anywhere的承诺。
- Spring的IoC容器降低了业务对象替换的复杂性，提高了组件之间的解耦。
- Spring的AOP支持允许将一些通用任务如安全、事务、日志等进行集中式管理，从而提供了更好的复用。
- Spring的ORM和DAO提供了与第三方持久层框架的良好整合，并简化了底层的数据库访问。
- Spring的高度开放性，并不强制应用完全依赖于Spring，开发者可自由选用Spring框架的部分或全部。



## 框架

![spring-overview](http://www.runoob.com/wp-content/uploads/2015/07/673670c9a34075831373b711cb8f21b7.png)



## Spring简介



spring是轻量级开源框架

spring是一个ioc(di)和aop容器框架，运用依赖注入和面向切面编程

spring是一个容器，包含并管理应用对象的生命周期

spring运用简单的组件配置合成一个复杂的应用，可以用xml和java注解来组合这些对象，并且可以整合各种企业应用的开源框架和第三方类库



###三种创建方式

- start.spring.io

- IDEA直接新建

- Spring TSL



### 核心机制



## 什么是Bean

对于开发者来说，开发者使用Spring框架主要是做两件事：①开发Bean；②配置Bean。对于Spring框架来说，它要做的就是根据配置文件来创建Bean实例，并调用Bean实例的方法完成"依赖注入"——这就是所谓IoC的本质。

#### 容器中Bean的作用域

当通过Spring容器创建一个Bean实例时，不仅可以完成Bean实例的实例化，还可以为Bean指定特定的作用域。Spring支持如下五种作用域：

1. singleton: 单例模式，在整个Spring IoC容器中，singleton作用域的Bean将只生成一个实例。
2. prototype: 每次通过容器的getBean()方法获取prototype作用域的Bean时，都将产生一个新的Bean实例。
3. request: 对于一次HTTP请求，request作用域的Bean将只生成一个实例，这意味着，在同一次HTTP请求内，程序每次请求该Bean，得到的总是同一个实例。只有在Web应用中使用Spring时，该作用域才真正有效。
4. session：该作用域将 bean 的定义限制为 HTTP 会话。 只在web-aware Spring ApplicationContext的上下文中有效。
5. global session: 每个全局的HTTP Session对应一个Bean实例。在典型的情况下，仅在使用portlet context的时候有效，同样只在Web应用中有效。

如果不指定Bean的作用域，Spring默认使用singleton作用域。prototype作用域的Bean的创建、销毁代价比较大。而singleton作用域的Bean实例一旦创建成果，就可以重复使用。因此，应该尽量避免将Bean设置成prototype作用域。



#### 创建bean的三种方式



1. 构造器创建Bean：

   使用构造器来创建Bean实例是最常见的情况，如果不采用构造注入，Spring底层会调用Bean类的无参数构造器来创建实例，因此要求该Bean类提供无参数的构造器。

   采用默认的构造器创建Bean实例，Spring对Bean实例的所有属性执行默认初始化，即所有的基本类型的值初始化为0或false；所有的引用类型的值初始化为null。



2. 使用静态工厂方法创建bean：

   使用静态工厂方法创建Bean实例时，class属性也必须指定，但此时class属性并不是指定Bean实例的实现类，而是静态工厂类，Spring通过该属性知道由哪个工厂类来创建Bean实例。

   除此之外，还需要使用factory-method属性来指定静态工厂方法，Spring将调用静态工厂方法返回一个Bean实例，一旦获得了指定Bean实例，Spring后面的处理步骤与采用普通方法创建Bean实例完全一样。如果静态工厂方法需要参数，则使用`<constructor-arg.../>`元素指定静态工厂方法的参数。

3. 调动实例工厂方法创建bean

   实例工厂方法与静态工厂方法只有一个不同：调用静态工厂方法只需使用工厂类即可，而调用实例工厂方法则需要工厂实例。先创建工厂本身作为一个bean，
   
`bean id="carFactory class = "InstanceCarFactory"`
   
再调用工厂实例。
   
   `<bean id="car2" factory-bean="carFactory" factory-method="getCar"></bean>`
   
   使用实例工厂方法时，配置Bean实例的`<bean.../>`元素无须class属性，配置实例工厂方法使用`factory-bean`指定工厂实例。
   采用实例工厂方法创建Bean的`<bean.../>`元素时需要指定如下两个属性：factory-bean: 该属性的值为工厂Bean的id。
   
   factory-method: 该属性指定实例工厂的工厂方法。
   
   若调用实例工厂方法时需要传入参数，则使用`<constructor-arg.../>`元素确定参数值。
   
4. FactoryBean去配置bean

   





## 依赖注入(Dependency Injection)

####`以配置文件来管理Java实例的协作关系`



- Spring框架的核心功能有两个：
  - Spring容器作为超级大工厂，负责创建、管理所有的Java对象，这些Java对象被称为Bean。
  - Spring容器管理容器中Bean之间的依赖关系，Spring使用一种被称为"依赖注入"的方式来管理Bean之间的依赖关系。

使用依赖注入，不仅可以为Bean注入普通的属性值，还可以注入其他Bean的引用。依赖注入是一种优秀的解耦方式，其可以让Bean以配置文件组织在一起，而不是以硬编码的方式耦合在一起。



- 当某个Java对象（调用者）需要调用另一个Java对象（被依赖对象）的方法时，在传统模式下通常有两种做法：	
  - 原始做法: 调用者**主动**创建被依赖对象，然后再调用被依赖对象的方法。
  - 简单工厂模式: 调用者先找到被依赖对象的工厂，然后**主动**通过工厂去获取被依赖对象，最后再调用被依赖对象的方法。

注意上面的**主动**二字，这必然会导致调用者与被依赖对象实现类的硬编码耦合，非常不利于项目升级的维护。使用Spring框架之后，调用者无需**主动**获取被依赖对象，调用者只要**被动**接受Spring容器为调用者的成员变量赋值即可，由此可见，使用Spring后，调用者获取被依赖对象的方式由原来的主动获取，变成了被动接受——所以Rod Johnson称之为控制反转。

另外从Spring容器的角度来看，Spring容器负责将被依赖对象赋值给调用者的成员变量——相当于为调用者注入它依赖的实例，因此Martine Fowler称之为依赖注入。

#### 设值注入

设值注入是指IoC容器通过成员变量的setter方法来注入被依赖对象。这种注入方式简单、直观，因而在Spring的依赖注入里大量使用。

#### 构造注入

利用构造器来设置依赖关系的方式，被称为构造注入。通俗来说，就是驱动Spring在底层以反射方式执行带指定参数的构造器，当执行带参数的构造器时，就可利用构造器参数对成员变量执行初始化——这就是构造注入的本质。

#### 两种注入方式的对比

设值注入有如下优点：

- 与传统的JavaBean的写法更相似，程序开发人员更容易理解、接受。通过setter方法设定依赖关系显得更加直观、自然。
- 对于复杂的依赖关系，如果采用构造注入，会导致构造器过于臃肿，难以阅读。Spring在创建Bean实例时，需要同时实例化其依赖的全部实例，因而导致性能下降。而使用设值注入，则能避免这些问题。
- 尤其在某些成员变量可选的情况下，多参数的构造器更加笨重。

构造注入优势如下：

- 构造注入可以在构造器中决定依赖关系的注入顺序，优先依赖的优先注入。
- 对于依赖关系无需变化的Bean，构造注入更有用处。因为没有setter方法，所有的依赖关系全部在构造器内设定，无须担心后续的代码对依赖关系产生破坏。
- 依赖关系只能在构造器中设定，则只有组件的创建者才能改变组件的依赖关系，对组件的调用者而言，组件内部的依赖关系完全透明，更符合高内聚的原则。

**注意：**
建议采用设值注入为主，构造注入为辅的注入策略。对于依赖关系无须变化的注入，尽量采用构造注入；而其他依赖关系的注入，则考虑采用设值注入。





#个人笔记

Spring 是什么

spring是容器框架，用于配置bean 并维护bean之间关系的框架

bean是java中的任何一种对象，javabean、service、action、数据源、dao，

spring框架可以管理web层，业务层，dao层，持久层，springweb项目只需要spring+数据库，spring可以配置各个层的组件(bean)并维护bean之间的关系

不需要new对象了，创建对象都在容器里设置，然后通过getBean获取

1. new容器对象，容器对象被创建，同时xml中配置，bean就会被创建，
2. 通过容器取到里面的bean对象
3. 调用bean对象的方法。

反射机制：先在容器中，内存中创建好了bean对象，再在service或者controller里面去调用它。

ioc：inverse of control 控制反转，***反转资源获取的方向***。

- 传统的资源查找方式要求组件向容器发起请求查找资源，作为回应，容器适时地返回资源，而应用了IOC之后，则是容器主动地将资源推送给它所管理的组件，组件所要做的仅是选择一种合适的方式来接受资源，也叫做查找的被动形式

DI：dependency injection 依赖注入，IOC的另一种表述方式，即：组件以一些预先定义好的方式(例如：setter方法)接受来自如容器的资源注入。

Attention：bean的class下id不能重复



####配置bean：

1. **配置形式：基于XML文件的方式；基于注解的方式**

2. **Bean的配置方式：通过全类名(反射)、通过工厂方法(静态工厂方法&实例工厂方法)、factoryBean**
3. **IOC容器 BeanFactory & ApplicationContext**
4. **依赖注入的方式：属性注入，构造器注入；**



####在Spring的IOC容器里配置bean

1. 在xml文件中通过bean节点来配置bean

   ```
   <bean id="User" class="entity.User">
       <property name="name" value="lingzhi"></property>
   </bean>
   
   class: bean的全类名，通过反射的方式在IOC容器中创建bean，所以要求bean中必须要有无参数的构造器
   id:   标识容器中的bean，id唯一
   ```

2. id：bean的名称，
   1. **在IOC中必须是唯一的**
   2. **若ID没有指定，spring自动将权限定性类名作为Bean的名字**
   3. id可以指定多个名字，名字之间用逗号，分号或空格分隔。



####Spring容器

在SpringIOC容器读取Bean配置创建bean实例之前，必须对他进行实例化，只有在容器实例化后，才可以从IOC容器中获取BEAN实例并使用

- BeanFactory：IOC容器的基本实现

- ApplicationContext：提供了更多的高级特性是BeanFactory的子接口

- BeanFactory是Spring框架的基础设施，面向Spring本身；

  ApplicationContext面向使用Spring框架的开发者，***几乎所有的应用场合都会直接使用ApplicationContext而非底层的BeanFactory***；

- 无论使用哪种方式，配置文件时相同

#### ApplicationContext

- 两个主要的实现类
  - **ClassPathXmlApplicationContext：从类路径下**加载配置文件
  - FileSystemXmlApplicationContext：从文件系统中加载配置文件

- ApplicationContext在初始化上下文时就实例化所有单例的bean

### 依赖注入的方式

- **属性注入：最常用的**

  - 通过setter方法注入bean的属性值或依赖的对象
  - 属性注入使用`<property>`元素使用`name`属性指定Bean的属性名称，`<value>`子节点或`value`属性指定属性值

- 构造器输入

  **使用构造器注入属性值可以指定参数的位置和参数的类型以区分重载的构造器**

- ```
  <bean id="User" class="entity.User">
      <constructor-arg value="LINGZHI" type="java.lang.String"></constructor-arg>
      <constructor-arg value="16" type = "int"></constructor-arg>
      <constructor-arg value="jiazhouhuayuan" index ="2"></constructor-arg>
  </bean>
  ```

  如果字面值包含特殊字符可以使用<![CDATA[]]>包裹起来

  属性值也可以使用value直接点进行配置

  ```
  <bean id="User" class="entity.User">
      <constructor-arg type="java.lang.String">
      		<value><![CDATA[<lingzhi!~>]]></value>
      </constructor-arg>
      <constructor-arg type = "int">	
      		<value>250</value>
      </constructor-arg>
      <constructor-arg value="jiazhouhuayuan" index ="2"></constructor-arg>
  </bean>
  ```

  bean的配置文件中，可以通过Property元素里的ref属性指定bean间的关系，为bean的属性或构造器参数指定对bean的引用

  ```
  <bean id="User1" class="entity.User">
      <property name="name" value="lingzhi"></property>
      <property name="age" value="16"></property>
      <property name="addr" value="jiazhouhuayuan"></property>
      <property name="car" ref="Car1"></property>
  </bean>
  ```

  也可以在属性和构造器里包含对bean的声明，这样的bean成为内部bean

  级联属性，集合属性（list，set，map.entry子节点[key,value]），
  
- 工厂方法输入(很少用)

### 自动注入

可以使用autowire属性指定自动装配的方式，

- byName 根据bean的名字和当前bean的setter的属性名进行自动装配，若由于匹配的则进行自动装配，没有的则不装配
- byType根据bean的类型和当前bean的属性的类型进行装配，问题是有重复（非唯一）的类型时会有异常

### 配置的继承和依赖

#### 继承

- Spring允许继承bean的配置，被继承的成为父bean，继承这个父bean的bean称为子bean。子bean从父bean中继承配置，包括属性配置(也可覆盖配置)

- 父bean作为模板配置，也可作为bean实例，若只想把父bean作为模板，可设置<bean>的abstract属性为true，变成抽象bean就不会被实例，

#### 依赖

- spring允许用户通过depends-on属性设定Bean前置依赖的bean
- 如果前置依赖于多个bean，则可以通过逗号，空格的方式配置bean的名称

### Bean的作用域

使用bean的scope属性来配置bean的作用域

1. singleton：默认值，容器初始时创建bean实例，整个容器的生命周期只创建这一个bean，单例的
2. prototype：原型，容器初始化时布局创建bean的实例，而在每次请求时都创建一个新的bean实例，并返回

### Bean的生命周期

初始方法：bean的 `init-method`属性，容器创建时，调用bean的初始化方法

销毁方法：bean的 `destory-method`属性，当容器关闭时，调用bean的销毁方法

后置处理器类 `implements BeanPostProcessor`，在init前后调用该方法。这是处理所有容器内的bean的处理器，需要增加条件函数来调用。

配置bean的后置处理器：不需要配置id，ioc容器自动识别是一个beanpostprocessor，只需要在ApplicationContext中加一个bean，例如

`<bean> class="MyBeanPostProcessor"></bean>`



### 工厂方法配置Bean

### FactoryBean来配置Bean

### 通过注解配置Bean

Spring能从classpath下自动扫描侦测和实例化具有特定注解的组件。可以spring的设置文件中声明<context:component-scan>来配置具体的哪个包需要扫描，

可以通过`resource-pattern`指定要扫描的类而非基包下的所有类，

`include-filter`和`exclude-filter`这两个子节点来指定排除或包括哪些指定表达式的组件

使用autowire，resource，inject来自动装配bean



### Spring AOP

#### why aop

为了解决**代码混乱**和**代码分散**的问题

so，使用**动态代理**来解决

AspectJ：使用aspectj注解和基于xml配置的aop

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



#### 前置通知



#### 后置通知



## 数据库与事务

### JDBC templete







## 框架

IOC是一个大的容器，来装很多不同的java 对象 俗称bean，

bean算一种变量对象，

bean装在容器里，变成了成员对象，有setter和getter方法，setter就是赋值，

注入方式有两种：设置注入和构造注入







ioc

aop

getBean是BeanFactory的一个方法，ApplicationContext是BeanFactory的子类所以也能用

> ## 什么是硬编码耦合
>
> 
>
> 