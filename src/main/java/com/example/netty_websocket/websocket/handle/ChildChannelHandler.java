package com.example.netty_websocket.websocket.handle;

import com.example.netty_websocket.websocket.util.SslUtil;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.ssl.SslContext;
import io.netty.handler.ssl.SslHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import io.netty.handler.timeout.IdleStateHandler;


import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLEngine;
import java.util.concurrent.TimeUnit;

/**
 * @author
 */
public class ChildChannelHandler extends ChannelInitializer<SocketChannel>{
    private static final String WEBSOCKET_PATH = "/websocket";

    private final SslContext sslCtx;
    public ChildChannelHandler(SslContext sslCtx) {
        this.sslCtx = sslCtx;
    }
    @Override
    public void initChannel(SocketChannel ch) throws Exception {
        ChannelPipeline pipeline = ch.pipeline();
//        if (sslCtx != null) {
//            pipeline.addLast(sslCtx.newHandler(ch.alloc()));
//        }
        SSLContext sslContext = SslUtil.createSSLContext("JKS","C://Users//Administrator//wss.jks","netty123");
        //SSLEngine 此类允许使用ssl安全套接层协议进行安全通信
        SSLEngine engine = sslContext.createSSLEngine();
        engine.setUseClientMode(false);

        pipeline.addLast(new SslHandler(engine));
        pipeline.addLast(new IdleStateHandler(5, 0, 0, TimeUnit.SECONDS));

        pipeline.addLast("http-codec",
                new HttpServerCodec());
        pipeline.addLast("aggregator",
                new HttpObjectAggregator(65536));
        ch.pipeline().addLast("http-chunked",
                new ChunkedWriteHandler());
        pipeline.addLast("handler",
                new MyWebSocketServerHandler(WEBSOCKET_PATH));
    }
}
