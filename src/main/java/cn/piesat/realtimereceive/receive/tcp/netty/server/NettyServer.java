package cn.piesat.realtimereceive.receive.tcp.netty.server;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.buffer.UnpooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * @author zk
 * @date 2018/9/29 14:39
 */
@Component
public class NettyServer {

    private Logger logger = LoggerFactory.getLogger(NettyServer.class);

    @Autowired
    private NettyServerInitializer nettyServerInitializer;

    @Value("${netty.port}")
    private int port;
    /**
     * 启动服务
     */
    public void startServer(){
        EventLoopGroup bossGroup = new NioEventLoopGroup();
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        try{
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup,workerGroup)
                    .channel(NioServerSocketChannel.class)
                    .option(ChannelOption.SO_BACKLOG,1024)
                    .childOption(ChannelOption.TCP_NODELAY,true)
                    .childOption(ChannelOption.SO_KEEPALIVE,true)
//                    .option(ChannelOption.ALLOCATOR,UnpooledByteBufAllocator.DEFAULT)
//                    .childOption(ChannelOption.ALLOCATOR,UnpooledByteBufAllocator.DEFAULT)
                    .childHandler(nettyServerInitializer);

            //绑定端口，接收数据
            ChannelFuture channelFuture = bootstrap.bind(port).sync();
            logger.info("netty服务启动：端口号：[" + port + "]");
            channelFuture.channel().closeFuture().sync();

        } catch (Exception e){
            e.printStackTrace();
            logger.error("netty服务启动异常-" + e.getMessage() );
        }

    }
}
