package com.xyzh.usermanager.api;

import com.xyzh.usermanager.entity.User;
import com.xyzh.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user")
public class UserApi {

    static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    UserService userService;

    /**
     * 根据ID查询用户信息
     * @param id
     * @return user
     */
    @GetMapping("/{id}")
    public User getById(@PathVariable Integer id){
        User user = userService.getUserById(id);
        logger.debug("debug+"+user);
        return user;
    }

    /**
     * 获取用户列表
     * @return userlist
     */
    @GetMapping("/list")
    public List<User> getAllUser(){
        List<User> userlist = userService.getAllUser();
        logger.debug("debug+"+userlist);

        return userlist;
    }

    /**
     * 根据返回数据新建用户
     * @param
     * @return
     */


    @PostMapping("/save")
    public Map<String,Object> addNewUser(@RequestBody rxUser rxuser){

        logger.debug("这是传入的数据"+ rxuser);
        User user = new User();
        user.setName(rxuser.getName());
        user.setAge(rxuser.getAge());
        user.setCreateDate(rxuser.getDate());
        user.setAddr(rxuser.getAddr());
        logger.debug("DEBUG+"+user);
        userService.addNewUser(user);

        Map map = new HashMap();
        map.put("success",true);
        return map;
    }

    /**
     * 根据新数据更新用户信息
     *
     */
    @PostMapping("/update")
    public Map<String,Object> updateUser(@RequestBody User rxuser){
        //User user = new User(id,name,age,date,addr);
        userService.updateUser(rxuser);
        Map map = new HashMap();
        map.put("success",true);
        return map;
    }


    @PostMapping("/remove")
    public Map<String,Object> deleteUser(@RequestBody RxId rxid){
        logger.debug("这是传入的数据"+ rxid.getId());
        //Integer uid = Integer.valueOf(id);
        userService.deleteUser(rxid.getId());
        Map map = new HashMap();
        map.put("success",true);
        return map;
    }


}
