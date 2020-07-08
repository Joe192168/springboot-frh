package com.aip.controller;

import com.aip.common.Message;
import com.alibaba.fastjson.JSON;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import javax.servlet.http.HttpServletRequest;
import java.util.Map;

/**
 * @program: springboot-frh
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-07-06 15:47
 **/
@Slf4j
@RestController
@RequestMapping("/user")
public class ApiController {

    @RequestMapping(value = "/getUser", method = RequestMethod.POST)
    public Message getUser(@RequestBody Map<String, Object> params, HttpServletRequest request){
        log.info("来自ip:{}, 请求参数:{}", request.getRemoteAddr(), JSON.toJSONString(params));
        //Integer id = (Integer) params.getOrDefault("id", 1);
        //int no = 1/0;
        return new Message().ok().addData("params",params);
    }

}
