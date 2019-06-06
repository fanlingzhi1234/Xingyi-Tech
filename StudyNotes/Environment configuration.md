# Environment configuration

####Author: Rexus;
####Date: 2019.5.15;



### JDK installation：

- target version：***jdk1.8.0-151-b12***

  Already installed

  1. java version "1.8.0_121"
  2.  Java(TM) SE Runtime Environment (build 1.8.0_121-b13)
  3. Java HotSpot(TM) 64-Bit Server VM (build 25.121-b13, mixed mode)

- 重新下载并安装 jdk1.8.0-211-b12
- 在***/etc/.profile***中新加java path 和classpath, `source`重置并 `echo path & CLASSPATH`检查是否添加成功。



###Maven download & configuration
​    already installed；



### IDEA download & configuration

    1. IDEA already installed
    2. plugins configuration:
        preference -> plugins -> search for target plugins -> download -> restart IDEA;
        (Free Mybatis, Lombok, Find bugs, rainbow brackets, )
    3. configure git commend and linked to Github account.
    4. check update.



### demo测试

1. 打开IDEA，使用spring initialize新建一个项目，选择新jdk包在的文件夹当做java版本，并project structure中添加依赖。
2. 在***/User/Rexus/.m2***文件夹中添加setting.xml
3. 运行 maven -> lifecycle -> install
4. 打开chrome，输入`localhost：8080`，提示whitepage，配置成功

