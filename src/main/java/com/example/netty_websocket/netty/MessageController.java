package com.example.netty_websocket.netty;

import com.example.netty_websocket.pojo.OrderResponse;
import com.example.netty_websocket.websocket.cache.Global;
import io.netty.channel.Channel;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
/**
 * @author
 */
public class MessageController {

    @RequestMapping("/send")
    public void send(){
        Channel channel = Global.channel.get("1");
        OrderResponse o = new OrderResponse();
        o.setOrderId("missusjcbscinb");
        TextWebSocketFrame textWebSocketFrame = new TextWebSocketFrame(o.toString());
        channel.writeAndFlush(textWebSocketFrame);
    }
}
