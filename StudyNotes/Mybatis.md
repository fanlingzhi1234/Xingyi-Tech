# MyBatis

Mybatis主要由四大部分组成：

①SqlSessionFactoryBuilder

②SqlSessionFactory

③SqlSession

④SQL Mapper

要想访问（操作）数据库：要建立数据库连接，要定义数据库操作方法（insert/update/delete...），要有具体的操作数据库中的表 的SQL语句，而SqlSessionFactoryBuilder、SqlSessionFactory、SqlSession就是用来负责底层建立数据库连接、管理连接、释放连接等。对于业务层而言，关心的是：定义一个SQL语句，让Mybatis方便地把SQL语句执行后的结果 呈现给使用者，而这可以通过SQL Mapper来完成。

SQL Mapper由两部分组成，一是：JAVA 接口，该接口中定义了 业务层 要对数据库进行何种操作；另一部分是：XML配置文件，定义了具体的数据库操作语句和映射规则。



MyBatis是一个支持**普通SQL查询**，**存储过程**和**高级映射**的优秀**持久层框架**。MyBatis消除了几乎所有的JDBC代码和参数的手工设置以及对结果集的检索封装。MyBatis可以使用简单的**XML或注解**用于配置和原始映射，将接口和Java的POJO（Plain Old Java Objects，普通的Java对象）映射成数据库中的记录。



## 理解mysql_mybatis字段映射表

| JDBC Type   | Java Type            |
| ----------- | -------------------- |
| CHAR        | String               |
| VARCHAR     | String               |
| LONGVARCHAR | String               |
| NUMERIC     | java.math.BigDecimal |
| DECIMAL     | java.math.BigDecimal |
| BIT         | boolean              |
| BOOLEAN     | boolean              |
| TINYINT     | byte                 |
| SMALLINT    | short                |

| JDBC Type     | JAVA Type     |
| ------------- | ------------- |
| INTEGER       | int           |
| BIGINT        | long          |
| REAL          | float         |
| FLOAT         | double        |
| DOUBLE        | double        |
| BINARY        | byte[]        |
| VARBINARY     | byte[]        |
| LONGVARBINARY | byte[]        |
| DATE          | java.sql.Date |

| JDBC Type | JAVA Type                       |
| --------- | ------------------------------- |
| TIME      | java.sql.Time                   |
| TIMESTAMP | java.sql.Timestamp              |
| CLOB      | Clob                            |
| BLOB      | Blob                            |
| ARRAY     | Array                           |
| DISTINCT  | mapping of underlying type      |
| STRUCT    | Struct                          |
| REF       | Ref                             |
| DATALINK  | java.net.URL[color=red][/color] |





### DEMO

#####role.java;q

```
package chapter2.pojo;

public class Role {
    private Long id;
    private String roleName;
    private String note;
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getRoleName() {
        return roleName;
    }
    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }
    public String getNote() {
        return note;
    }
    public void setNote(String note) {
        this.note = note;
    }
    
    @Override
    public String toString() {
        return "id:" + id + ", roleName:" + roleName + ", note:" + note;
    }
}
```



#####roleService.java 接口类

```package chapter2.mapper;
import java.util.List;
import java.util.Map;
import chapter2.pojo.Role;

public interface RoleMapper {
    public Role getRole(Long id);
    public int deleteRole(Long id);
    public int insertRole(Role role);
    public List<Role> findRoleByMap(Map<String, String> params);
}
```



#####roleMapper.xml,  xml配置文件，声明方法

```<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<mapper namespace="chapter2.mapper.RoleMapper">

<resultMap type="chapter2.pojo.Role" id="roleMap">
<id property="id" column="id"/><!-- primary key -->
<result property="roleName" column="role_name"/><!-- 普通列的映射关系 -->
<result property="note" column="note"/>
</resultMap>

		<!-- 用ID搜索用户 -->
    <select id="getRole" parameterType="long" resultType="Role">
        select id, role_name as roleName, note from t_role where id = #{id}
    </select>
    <insert id="insertRole" parameterType="Role">
        insert into t_role(role_name,note) values(#{roleName},#{note})
    </insert>
    <delete id="deleteRole" parameterType="long">
        delete from t_role where id = #{id}
    </delete>
    
    <select id="findRoleByMap" parameterType="map" resultMap="roleMap">
        select id,role_name,note from t_role
        where role_name like concat('%', #{roleName},'%')
        and note like concat('%',#{note},'%')
    </select>
</mapper>
```



然后，就可以用SqlSessionFactory创建SqlSession，SqlSession获取相应的RoleMapper实例，再使用RoleMapper实例调用RoleMapper接口中定义的方法，最终由Mybatis根据 RoleMapper.xml配置文件将 方法 与 映射成具体的数据库操作语句，最终访问数据库。

使用SqlSessionFactoryBuilder **根据 mybatisConfig.xml中配置的 dataSource创建SqlSessionFactory，再使用SqlSessionFactory创建SqlSession**。



#####main()函数调用

```package chapter2.main;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSession;

import chapter2.mapper.RoleMapper;
import chapter2.pojo.Role;
import chapter2.util.SqlSessionFactoryUtil;

public class Chapter2Main {
    public static void main(String[] args)throws IOException {
        SqlSession sqlSession = null;
        
        try {
            sqlSession = SqlSessionFactoryUtil.openSqlSession();
            RoleMapper roleMapper = sqlSession.getMapper(RoleMapper.class);
            
            Role role = new Role();
            role.setRoleName("testName");
            role.setNote("testNote");
            roleMapper.insertRole(role);
            //roleMapper.deleteRole(1L);
            sqlSession.commit();
            
            Map<String, String> paramsMap = new HashMap<>();
            paramsMap.put("roleName", "me");
            paramsMap.put("note", "no");//与sql语句中的 #{note} #{roleName}一致
            List<Role> result = roleMapper.findRoleByMap(paramsMap);
            for (Role role2 : result) {
                System.out.println(role2);
            }
            
        }catch(Exception e) {
            System.out.println(e);
            sqlSession.rollback();
        }finally {
            if(sqlSession != null)
                sqlSession.close();
        }
        
    }
}
```



##### Mybatis配置数据库连接: mybatisConfig.xml

```<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE configuration PUBLIC "-//mybatis.org//DTD Config 3.0//EN"    
"http://mybatis.org/dtd/mybatis-3-config.dtd">
<configuration>
    <properties resource="jdbc.properties">
    </properties>
    <typeAliases>
        <typeAlias alias="Role" type="chapter2.pojo.Role" />
    </typeAliases>

    <environments default="development">
        <environment id="development">
            <transactionManager type="JDBC">
                <property name="autoCommit" value="false" />
            </transactionManager>
            <dataSource type="POOLED">
                <property name="driver" value="${driver}" />
                <property name="url" value="${url}" />
                <property name="username" value="${username}" />
                <property name="password" value="${password}" />
            </dataSource>
        </environment>
    </environments>

    <mappers>
        <mapper resource="chapter2\mapper\RoleMapper.xml" />
    </mappers>
</configuration>
```

Mybatis的XML配置文件定义了许多配置标签：比如 <configuration> <properties> <settings> <typeAliases> ....等标签。

**这些标签是有层次结构的，顺序不能乱。比如，<properties>标签应该放在 <typeAliases> 标签前面。**

上面的第5行<properties>标签 通过  **resource 指定一个外部jdbc配置文件**，这样在16-21行配置 数据源 的时候，就可以使用 变量 来引用 外部jdbc配置文件中定义的值了，从而方便切换数据库配置。

外部jdbc配置文件如下：

```
driver=com.mysql.jdbc.Driver
url=jdbc:mysql://localhost:3306/test
username=root
password=xxxx
```





#### 解释

下面来重点解释：操作数据库的XML配置文件RoleMapper.xml 和 接口RoleMapper 以及POJO类RoleMapper之间的一些关系：

RoleMapper 接口中定义的getRole方法：

```
public Role getRole(Long id);
```

 

RoleMapper.xml配置文件中与该方法对应的SQL语句：

```
    <select id="getRole" parameterType="long" resultType="Role">
        select id, role_name as roleName, note from t_role where id = #{id}
    </select>
```

id 用来唯一标明这条 select 语句。它与RoleMapper.xml中 <mapper namespace>标签内容组合在一起，唯一标识了 这条 select 操作语句。*这里的resultType="Role"，"Role"是在mybatisConfig.xml中定义的别名。*

```
<mapper namespace="chapter2.mapper.RoleMapper">

public Role getRole(Long id);

<select id="getRole" parameterType="long" resultType="Role">


id 的值getRole 就是接口RoleMapper中 定义的 getRole方法名
parameterType 就是getRole方法的参数类型
resultType 就是执行SQL语句后返回的结果，把结果 封装 成POJO类 Role 类型---这也是getRole方法的返回类型。而方法的参数 会传递给#{id}
```

