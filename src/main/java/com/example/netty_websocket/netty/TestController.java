package com.example.netty_websocket.netty;

import com.example.netty_websocket.pojo.OrderResponse;
import com.example.netty_websocket.websocket.cache.Global;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @RequestMapping("/test1")
    public void test1(){
        Channel channel = Global.channel.get("1");
        OrderResponse o = new OrderResponse();
        o.setOrderId("missusjcbscinb");
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(o.toString());
        channel.writeAndFlush(textWebSocketFrame);
    }
    @RequestMapping("/test2")
    public void test2(){
        Channel channel = Global.channel.get("2");
        OrderResponse o = new OrderResponse();
        o.setOrderId("huanglei");
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(o.toString());
        channel.writeAndFlush(textWebSocketFrame);
    }
}
