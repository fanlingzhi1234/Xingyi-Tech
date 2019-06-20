package com.xyzh.usermanager.api;

import com.xyzh.usermanager.entity.User;
import com.xyzh.usermanager.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/user")

public class UserApi {

    static final Logger logger = LoggerFactory.getLogger(UserApi.class);

    @Autowired
    UserService userService;

    /**
     * 根据ID查询用户信息
     * @param id
     * @return
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
     * @param user
     * @return
     */
    @PostMapping("/save")
    public void addNewUser(@RequestBody User user){

        User datauser = userService.getUserById(user.getId());
        String flagMessage = "success";
        String hintMessage="数据添加成功!!!";
        if(datauser != null){
            flagMessage = "fail";
            hintMessage = "数据库已有该数据!!!";
        }else{
            userService.addNewUser(user.getName(),user.getAge(),user.getDate(),user.getAddr());

        }

//
//
//
//        String flagMessage = "success";
//        String hintMessage="数据添加成功!!!";
//        if(datauser != null){
//            flagMessage = "fail";
//            hintMessage = "数据库已有该数据!!!";
//        }else{
//            userService.addNewUser(user.getName(),user.getAge(),user.getDate(),user.getAddr());
//        }
//
//        JSONObject jsonObject = new JSONObject();
//        jsonObject.put("flag",flagMessage);
//        jsonObject.put("hintMessage",hintMessage);
//        System.out.println(jsonObject.toJSONString());
    }

    /**
     *
     * @param id
     * @param name
     * @param age
     * @param addr
     * @param date
     */
    @PostMapping("/update")
    public void updateUser(Integer id, String name, Integer age, String addr, java.sql.Date date){
        userService.updateUser(new User(id,name,age,date,addr));
    }


    //    @PostMapping("/update/{id}")
//    public ResponseData update(@PathVariable Long id,
//                               @RequestBody @Valid RxUpdateUser rxUpdateUser,
//                               BindingResult bindingResult) {
//        if (bindingResult.hasFieldErrors()) {
//            return ResponseUtils.valid(bindingResult);
//        }
//        UserInfo userInfo = new UserInfo(id);
//        userInfo.setName(rxUpdateUser.getName());
//        userInfo.setCardId(rxUpdateUser.getCardId());
//        userInfo.setSex(rxUpdateUser.getSex());
//        if (rxUpdateUser.getBirthday() != null) {
//            userInfo.setBirthday(rxUpdateUser.getBirthday());
//        }
//        userInfo.setState(UserState.ACTIVE);
//        userInfo.setRemark(rxUpdateUser.getRemark());
//        userService.update(userInfo);
//
//        return ResponseData.success();
//    }



    @PostMapping("/remove")
    public void deleteUser(Integer id){
        userService.deleteUser(id);
    }


}
