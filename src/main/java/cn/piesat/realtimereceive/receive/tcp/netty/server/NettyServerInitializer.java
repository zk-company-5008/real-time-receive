package cn.piesat.realtimereceive.receive.tcp.netty.server;

import cn.piesat.realtimereceive.receive.tcp.netty.code.NettyMessageDecode;
import cn.piesat.realtimereceive.receive.tcp.netty.handler.NettyServerHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * @author zk
 * @date 2018/9/29 14:48
 */
@Component
@Scope("prototype")
public class NettyServerInitializer extends ChannelInitializer<SocketChannel> {

    @Autowired
    private NettyServerHandler nettyServerHandler;

    @Autowired
    private NettyMessageDecode nettyMessageDecode;

    @Override
    protected void initChannel(SocketChannel socketChannel) throws Exception {
        ChannelPipeline pipeline = socketChannel.pipeline();
        pipeline.addLast(nettyMessageDecode);
        pipeline.addLast(nettyServerHandler);

    }
}
