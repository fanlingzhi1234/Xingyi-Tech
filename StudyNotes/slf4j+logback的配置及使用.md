### slf4j+logback的配置及使用

https://www.jianshu.com/p/696444e1a352

https://blog.csdn.net/qazwsxpcm/article/details/79167503 (简单版)

https://www.cnblogs.com/warking/p/5710303.html (完整版)

Logback介绍
Logback是由log4j创始人设计的又一个开源日志组件。logback当前分成三个模块：logback-core,logback- classic和logback-access。logback-core是其它两个模块的基础模块。logback-classic是log4j的一个 改良版本。此外logback-classic完整实现SLF4J API使你可以很方便地更换成其它日志系统如log4j或JDK14 Logging。logback-access访问模块与Servlet容器集成提供通过Http来访问日志的功能。

Logback的优点
执行速度快，初始化内存加载小;
原生实现了 SLF4J API，不需要进行转换;
配置简单，并可以适应多种环境;
可以定时的删除过期日志;
更为强大的过滤器，不必因更改日志级别而产生大量的日志;
可以从 IO 错误中进行恢复;
…. 
关于logback的优点还有很多，这里我就不一一列举了。详细的可以查看官方的Api文档。 地址:https://logback.qos.ch/documentation.html

