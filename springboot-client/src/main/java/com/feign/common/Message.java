package com.feign.common;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * 前后端统一消息定义协议 Message  之后前后端数据交互都按照规定的类型进行交互
 */
public class Message {

    private final int SC_OK = 200;
    private final int SC_INTERNAL_SERVER_ERROR = 500;

    // 消息头meta 存放状态信息 code message
    private Map<String,Object> meta = new HashMap<String,Object>();
    // 消息内容  存储实体交互数据
    private Map<String,Object> data = new HashMap<String,Object>();

    public Map<String, Object> getMeta() {
        return meta;
    }

    public Message setMeta(Map<String, Object> meta) {
        this.meta = meta;
        return this;
    }

    public Map<String, Object> getData() {
        return data;
    }

    public Message setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }
    public Message addMeta(String key, Object object) {
        this.meta.put(key,object);
        return this;
    }
    public Message addData(String key,Object object) {
        this.data.put(key,object);
        return this;
    }
    public Message ok(int statusCode,String statusMsg) {
        this.addMeta("success",Boolean.TRUE);
        this.addMeta("code",statusCode);
        this.addMeta("msg",statusMsg);
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }
    public Message error(int statusCode,String statusMsg) {
        this.addMeta("success",Boolean.FALSE);
        this.addMeta("code",statusCode);
        this.addMeta("msg",statusMsg);
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }

    public Message ok() {
        this.addMeta("success",Boolean.TRUE);
        this.addMeta("code",SC_OK);
        this.addMeta("msg","操作成功");
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }


    public Message error() {
        this.addMeta("success",Boolean.FALSE);
        this.addMeta("code",SC_INTERNAL_SERVER_ERROR);
        this.addMeta("msg","操作失败");
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }

    /*
    *@Description 接口调用成功使用，参数为自定义信息
    *@Author LF
    *@Date  2019/11/29 
    *@Param [statusMsg]
    *@return com.geominfo.bi.domain.vo.Message
    **/  
    public Message ok(String statusMsg) {
        this.addMeta("success",Boolean.TRUE);
        this.addMeta("code", SC_OK);
        this.addMeta("msg",statusMsg);
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }


    /*
    *@Description 接口调用失败或者调用异常时使用，参数为失败信息或者异常信息
    *@Author LF
    *@Date  2019/11/29 
    *@Param [statusMsg]
    *@return com.geominfo.bi.domain.vo.Message
    **/  
    public Message error(String statusMsg) {
        this.addMeta("success",Boolean.FALSE);
        this.addMeta("code", SC_INTERNAL_SERVER_ERROR);
        this.addMeta("msg",statusMsg);
        this.addMeta("timestamp",new Timestamp(new Date().getTime()));
        return this;
    }
    /*
     * @description: 操作成功返回一个对象用此方法
     * @author: 李英杰
     * @date: 2019/9/2
     * @param: [content]
     * @return: com.geominfo.authing.common.base.Message
     */
    public static Message ok(Object content) {
        return new Message(content);
    }

    public Message(Object content) {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", SC_OK);
        this.addMeta("msg","操作成功");
        this.addMeta("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        this.addData("content", content);
    }

    public Message() {
        this.addMeta("success", Boolean.TRUE);
        this.addMeta("code", SC_OK);
        this.addMeta("msg","操作成功");
        this.addMeta("timestamp", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
    }
}
