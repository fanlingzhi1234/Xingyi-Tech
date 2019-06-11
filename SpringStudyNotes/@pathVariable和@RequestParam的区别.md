### @pathVariable和@RequestParam的区别

1. 用法上的不同： 
   从名字上可以看出来，PathVariable只能用于接收url路径上的参数，而RequestParam只能用于接收请求带的params 
   看下面一个例子：

```
package com.lrm.springbootdemo.web;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/v1")
public class HelloController {

@GetMapping("/books/{username}")
public Object testPathVariable(@PathVariable String username){
    Map<String,Object> map = new HashMap<>();
    map.put("username",username);
    return map;
}

@PostMapping("/books2")
public Object testRequestParam(@RequestParam("name") String name,
                   @RequestParam("author") String author,
                   @RequestParam("isbn") String isbn) {
    Map<String, Object> book = new HashMap<String, Object>();
    book.put("name", name);
    book.put("author", author);
    book.put("isbn", isbn);
    return book;
}
@PostMapping("/books2/{id}")
public Object test(@PathVariable("id") long id,@RequestParam("name") String name,
                   @RequestParam("author") String author,
                   @RequestParam("isbn") String isbn) {
    Map<String, Object> book = new HashMap<String, Object>();
    book.put("id",id);
    book.put("name", name);
    book.put("author", author);
    book.put("isbn", isbn);
    return book;
}}

```

其中testPathVariable这个方法中的username参数只能使用@PathVariable来接收，因为username参数是url的path上携带的参数。username是无法使用RequestParam来接受的。 

testRequestParam这个方法只能用于 
localhost:8080/api/v1/books2/12?name=java in action&author=ric&isbn=dsdas2334 
这种模式的请求，因为RequestParam只能用于接收请求上带的params，testPathVariable是无法接收上面的name、author、isbn参数的。

2. 内部参数不同 
   PathVariable有value，name，required这三个参数，而RequestParam也有这三个参数，并且比PathVariable多一个参数defaultValue（该参数用于当请求体中不包含对应的参数变量时，参数变量使用defaultValue指定的默认值）

3. PathVariable一般用于get和delete请求，RequestParam一般用于post请求。

