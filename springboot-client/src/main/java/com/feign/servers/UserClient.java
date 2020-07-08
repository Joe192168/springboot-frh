package com.feign.servers;

import com.alibaba.fastjson.JSON;
import com.feign.common.Message;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.Map;

/**
 * @program: springboot-frh
 * @description:
 * @author: xiaoqiaohui
 * @create: 2020-07-06 16:10
 **/
@FeignClient(name="user",url = "${api.ribbon.servers}",fallback = UserClient.UserFallback.class)
public interface UserClient {

    @RequestMapping(value="/user/getUser",method = RequestMethod.POST,consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    Message testFeign(Map<String, Object> data);

    @Slf4j
    @Component
    class UserFallback implements UserClient {
        @Override
        public Message testFeign(Map<String, Object> data) {
            log.error("fallback:" + JSON.toJSONString(data));
            return new Message().error("获取用户信息失败");
        }
    }

}
