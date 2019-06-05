package com.example.springmvcdemo;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/message")
public class MessageController {

    //把Requestbody整个message对象都当成content了，
    @GetMapping("/single")
    public String getMessage(@RequestParam("content") String content,@RequestParam("sender") String sender, @RequestParam("receiver[]") List<String> receiver){
        return "single message get , content, sender, receiver = "+content + sender + receiver;
        //return new Message(content, sender, receiver);
    }
    // 三个值都是null
    @GetMapping("/receiverList")
    public Message getReceiver(Message message){
        return message;
    }
    //必须要加requestbody，不加就默认是responsebody
    @GetMapping("/receiverList2")
    public String getReceiver2(@RequestBody Message message ){
        return message.getContent();
    }

    /*

    {
	"content":"你好",
	"sender":"Jill",
	"receiver":["bob",
				"mom",
				"dad"]
    }
        result = receiver post [bob, mom, dad]
     */
    // 传递 list表单值
//    @PostMapping("/user/list2")
//    public String list(@RequestParam("listParam[]") List<String> param) {
//        return "Request successful. Post param : List<String> - " + param.toString();
//    }
    @PostMapping("/receiverList")
    public String postReceiver(@RequestBody Message message){
        System.out.println(message.getReceiver());
        return "receiver post "+ message.getReceiver();
    }
    //传入各类属性，组成一个message，post一个message
    //传递list，必须要加@RequestParam和list变量名
    @PostMapping("/single")
    public String postSingleMessage(String content, String sender,@RequestParam("receiver[]") List<String> receiver){
        System.out.println(receiver.size());
        return "post sucessful + "+new Message(content,sender,receiver);
    }

    //直接post一个message类型，用ModelAttribute
    @PostMapping("/conform")
    public String requestMessage(@ModelAttribute Message message, String abc) {
        return "Get request is successful. Post param : User Class - " + message.toString();
    }
    @PostMapping("/single2")
    public Message postSingleMessage2(@RequestBody Map<String, String> map){
        String content = map.get("content");
        String sender = map.get("sender");
        System.out.println(content);
        System.out.println(sender);

        return new Message(content, sender);
    }
    @GetMapping("/validate")
    public String validate(@Valid Message message, BindingResult result) {
        if (result.hasErrors()) {
            return "Object has validation errors";
        } else {
            return "No errors";
        }
    }
}
