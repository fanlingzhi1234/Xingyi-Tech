##Pageable



Pageable 是Spring Data库中定义的一个接口，该接口是所有分页相关信息的一个抽象，通过该接口，我们可以得到和分页相关所有信息（例如pageNumber、pageSize等）。

Pageable定义了很多方法，但其核心的信息只有两个：一是分页的信息（page、size），二是排序的信息。

在请求中只需要在方法的参数中直接定义一个pageable类型的参数，当Spring发现这个参数时，Spring会自动的根据request的参数来组装该pageable对象。
 Spring支持的request参数如下：

```java
page ：第几页，从0开始，默认为第0页
size ：每一页的大小，默认为10
sort ：排序相关的信息，以`property[,ASC|DESC]`的方式组织，例如`sort=firstname&sort=lastnam
```