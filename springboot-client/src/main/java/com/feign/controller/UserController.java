package com.feign.controller;

import com.feign.common.Message;
import com.feign.servers.UserClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * @program: springboot-frh
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-07-06 16:42
 **/
@RestController
public class UserController {

    @Autowired
    private UserClient userClient;

    @PostMapping("/getUser")
    public Message getUser(@RequestBody Map<String,Object> map){
        return userClient.testFeign(map);

    }

}
