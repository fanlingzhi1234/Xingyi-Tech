### Spring mvc 中 @requestParam和@RequestBody浅析



#### @RequestParam 

A） 常用来处理简单类型的绑定，通过Request.getParameter() 获取的String可直接转换为简单类型的情况（ 由String到 简单类型的转换操作由ConversionService配置的转换器来完成）；因为使用request.getParameter()方式获取参数，所以***可以处理get 方式中queryString的值，也可以处理post方式中 body data的值。***


B）用来处理Content-Type: 为 application/x-www-form-urlencoded编码的内容，提交方式GET、POST。(不设置这个属性，好像这就是默认值)

C) 该注解有两个属性： value、required； value用来指定要传入值的id名称，required用来指示参数是否必须绑定。

在方法参数里面如是：

```
public @ResponseBody JsonResult getPublishedToposByConnStreamId(@RequestParam(value = "streamId", required = false) String streamId) {}
```

#### @RequestBody

A) GET、POST方式提时， 根据request header Content-Type的值来判断:
    application/x-www-form-urlencoded， 可选（即非必须，因为这种情况的数据@RequestParam, @ModelAttribute也可以处理，当然@RequestBody也能处理）；
    multipart/form-data, 不能处理（次类型多用来上传文件类型---即使用@RequestBody不能处理这种格式的数据,@RequestParam这个却是可以处理的。）；
    其他格式，***必须（其他格式包括application/json, application/xml等。这些格式的数据，必须使用@RequestBody来处理)***；

B) PUT方式提交时， 根据request header Content-Type的值来判断:(表示没见过put方式滴，可以无视吧。)
    application/x-www-form-urlencoded， 必须；
    multipart/form-data, 不能处理；
    其他格式， 必须；





1 . 后台接受参数的类型

-  直接把表单的参数写在Controller相应的方法的形参中，适用于get方式提交，不适用于post方式提交。

        如 :  public String addUser1(String username,@RequestParam String password) 形参上可以添加@RequestParam注解


- 参数过多 , 封装成javabean , 适合用post提交 , 形参使用@RequestBody注解





后台 : public String addUser1(String username,@RequestParam String password)

 

request中发送json数据用post方式发送Content-type用application/json;charset=utf-8方式发送的话，直接用springMVC的@RequestBody标签接收后面跟实体对象就行了，spring会帮你自动拼装成对象

如果Content-type设置成application/x-www-form-urlencoded;charset=utf-8就不能用spring的东西了，只能以常规的方式获取json串了



附上一点常用的Content-type的方式

application/x-javascript text/xml->xml数据 application/x-javascript->json对象 application/x-www-form-urlencoded->表单数据 application/json;charset=utf-8 -> json数据

 

form-data数据 :　就是http请求中的multipart/form-data,既可以上传文件等二进制数据,也可以上传表单键值对,只是最后会转化为一条信息. 一般上传文件，图片用x-www-form-urlencoded数据：就是application/x-www-from-urlencoded,会将表单内的数据转换为键值对, 比如 : name=Java&age = 23



***前台  post + x-www-form-urlencoded数据   后台public String addUser1(String username,String password)获取数据正常***

***前台  post + x-www-form-urlencoded数据  后台public add(String username,@RequestParam String password)获取数据正常***

前台  post + json数据　　 后台public String addUser1(String username,String password)全部是ｎｕｌｌ

前台  post + json数据        后台public add(String username,@RequestParam String password)报错password找不到

前台  post + json数据        后台public add(String username,@RequestParam String password)报错

前台  post + json数据        后台public add(@RequestParamString username, String password)username获取全部的json数据,                                              而password是null

***前台  post + json数据       后台只适合＠RequestBody封装对象的形式***