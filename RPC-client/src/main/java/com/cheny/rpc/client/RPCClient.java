package com.cheny.rpc.client;

import com.cheny.rpc.common.bean.RPCRequest;
import com.cheny.rpc.common.bean.RPCResponse;
import com.cheny.rpc.common.codec.RPCDecoder;
import com.cheny.rpc.common.codec.RPCEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;




/**
 * @ClassName RPCClient
 * @Description
 * @Author cheny
 * @Date 2021/11/21 15:51
 * @Version 1.0
 **/
public class RPCClient extends SimpleChannelInboundHandler<RPCResponse> {
    //日志对象
    private static final Logger LOGGER = LoggerFactory.getLogger(RPCClient.class);

    //传输信息的服务端的ip和端口
    private final String host;
    private final int port;

    //服务端反馈的response信息对象
    private RPCResponse response;

    public RPCClient(String host, int port) {
        this.host = host;
        this.port = port;
    }


    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, RPCResponse prcResponse) throws Exception {
        this.response = response;
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        LOGGER.error("api caught exception",cause);//日志记录异常原因
        ctx.close();//关闭上下文对象
    }
    //发送rpc请求
    public RPCResponse send(RPCRequest request) throws InterruptedException {

        EventLoopGroup  group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel socketChannel) throws Exception {
                            ChannelPipeline pipeline = socketChannel.pipeline();
                            pipeline.addLast(new RPCEncoder(RPCRequest.class));//注册编码器
                            pipeline.addLast(new RPCDecoder(RPCResponse.class));//注册解码器
                            pipeline.addLast(RPCClient.this);//注册客户端信息
                        }
                    })
                    .option(ChannelOption.TCP_NODELAY, true);
            ChannelFuture future = bootstrap.connect(host, port).sync();
            Channel channel = future.channel();
            channel.writeAndFlush(request).sync();
            channel.closeFuture().sync();
            return response;
        }finally {
            group.shutdownGracefully();
        }
    }



















}
