## Spring MVC 学习笔记

### 常用的注解

**@Controller**

注解一个类表示控制器,Spring MVC 会自动扫描标注了这个注解的类。

**@RequestMapping**

请求路径映射，可以标注类，也可以标注方法，可以指定请求类型（post、get、put、delete、patch...）默认不指定为全部接收。

**@RequestParam**

用于数据绑定，接收url中的参数（即 **Content-Type : application/x-www-form-urlencoded**类型的内容），并且前后端参数名称必须相同。

@RequestParam注解主要有哪些参数：

value：参数名字，即入参的请求参数名字，如username表示请求的参数区中的名字为username的参数的值将传入；

required：是否必须，默认是true，表示请求中一定要有相应的参数，否则将报404错误码；

defaultValue：默认值，表示如果请求中没有同名参数时的默认值，

**@RequestBody**

用于数据绑定，接收body体中的参数，而不是url。所以请求一定是post（即不是 **Content-Type : application/x-www-form-urlencoded** 类型的内容）

@RequestBody接收的是一个Json对象的字符串，而不是一个Json对象。它只能绑定一个变量，可以是对应的变量类，也可以是字符串。

**@ResponseBody**

放在方法上，表示此方法返回的数据放在body体中，而不是跳转页面。一般用于ajax请求，返回json数据。

**@RestController**

这个是**@Controller**和**@ResponseBody**的注解组合。

**@PathVariable**

路径绑定变量，用于绑定路径中 “{a}”的变量。

**@RequestHeader**

放在参数前，用来获取request header中的参数。

**@CoookieValue**

放在方法参数前，用来获取request header cookie中的参数值。

**@GetMapping，@PostMapping，@DeleteMapping**

Spring4.3之后加入的注解，来表示指定的请求方式。



- 多个相同前缀的url可以在@controller后添加@RequestMapping前缀的url直到差异的部分。例如`/api/v1/user` 然后子字符串可能有`list`,`profile`,`login`等



### controller和restcontroller区别

@controller是spring带的注解，最基础的注解。

@restcontroller 默认返回一个json 对象，所以不用添加@response body

如果用@controller就需要在每一个方法返回对应值的地方添加@responseBody，例如controller

```controller
@controller
public @ResponseBody User profile (){ return new User();}

@Restcontroller
public User profile (){ return new User();}
```



### Get和post传输值的三种方式

#### url传值

有两种传值方式，一种是在url中指定key-value，



```
@PostMapping("/user/profile")
public String postName(String name){
    return "this is post method from + "+ name;
}
```

localhost:3000/user/profile?name=fanlingzhi&addr=chongqing

```
@PostMapping("/user/list")
public User postUser(String name,String addr,Integer age){
    return new User(name,addr,age);
}

```

也可以直接在url中添加你要传入的值。下图映射地址添加了`/{name}`，需在申明时增加@PathVariable注解供搜索。

当申明时变量名和url中引用的变量名一致时，则@PathVariable() 括号内只用默认值，若不一致，如/user/list/{name2}则应该改为@PathVariable("name2")

```
@PostMapping("/user/list/{name}")
public User postUserbyName(@PathVariable String name){
    return new User(name,"123",0);
}
```

request url：`Localhost:3000/user/list/list/fanlingzhi(不需要加大括号)`



#### request Body传值(json传值)

在postman中可以设定request body的

```
@PostMapping("/user/save")
public User save(Map<String,Object> data){
    System.out.println("name is "+data.get("name"));
    return new User();
}
```

#### 容器传值





### List类型的值的传递

关于`List`做为Controller参数，除了`String`本人还测试`Integer`以及`Double`类型，其他类型未作测试。这里面`@RequestParam()`必须增加**"listParam[]"**如果你的Web端是别的名字，换成相应名称即可，如果只用`@RequestParam`注解的话，请求会接到`HTTP Status 400 - Required List parameter 'xxx' is not present`的提示信息。

例如：

```
@PostMapping("/user/list2")
	public String list(@RequestParam("listParam[]") List<String> param) {
	return "Request successful. Post param : List<String> - " + param.toString();
    }
```



@RequestBody

@ModelAttribute

@PathVariable

@Valid

@NotNUll， @NotEmpty， @NotBlank

| **@Null**                     | 限制只能为null                                               |
| ----------------------------- | ------------------------------------------------------------ |
| **@NotNull**                  | 限制必须不为null                                             |
| **@AssertFalse**              | 限制必须为false                                              |
| **@AssertTrue**               | 限制必须为true                                               |
| **@DecimalMax(value)**        | 限制必须为一个不大于指定值的数字                             |
| **@DecimalMin(value)**        | 限制必须为一个不小于指定值的数字                             |
| **@Digits(integer,fraction)** | 限制必须为一个小数，且整数部分的位数不能超过integer，小数部分的位数不能超过fraction |
| **@Future**                   | 限制必须是一个将来的日期                                     |
| **@Max(value)**               | 限制必须为一个不大于指定值的数字                             |
| **@Min(value)**               | 限制必须为一个不小于指定值的数字                             |
| **@Past**                     | 限制必须是一个过去的日期                                     |
| **@Pattern(value)**           | 限制必须符合指定的正则表达式                                 |
| **@Size(max,min)**            | 限制字符长度必须在min到max之间                               |





### File upload

```
MultipartFile file
```





validate

```
@GetMapping("/validate")
public String validate(@Valid Message message, BindingResult result) {
    if (result.hasErrors()) {
        return "Object has validation errors";
    } else {
        return "No errors";
    }
}
```