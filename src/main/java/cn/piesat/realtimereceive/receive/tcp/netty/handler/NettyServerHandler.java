package cn.piesat.realtimereceive.receive.tcp.netty.handler;

import cn.piesat.realtimereceive.receive.tcp.netty.cache.ConnectCache;
import cn.piesat.realtimereceive.send.ISendToKafka;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.util.CharsetUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.atomic.AtomicInteger;


/**
 * @author zk
 * @date 2018/9/29 16:06
 */
@Component
@Scope("prototype")
@ChannelHandler.Sharable
public class NettyServerHandler extends ChannelInboundHandlerAdapter {

    private Logger logger = LoggerFactory.getLogger(NettyServerHandler.class);

    @Autowired
    private BusinessHandler businessHandler;
    @Autowired
    private ISendToKafka iSendToKafka;

    private AtomicInteger atomicInteger = new AtomicInteger(0);
    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        //业务处理
        /*List<byte[]> list = (List<byte[]>) msg;
        atomicInteger.addAndGet(list.size());
        System.out.println("已完成【"+ atomicInteger.get() +"】");*/
//        for (byte[] bytes : list) {
//            iSendToKafka.sendCustomTopic("RTSToSZ",bytes);
//        }
        businessHandler.dealAndSend(ctx , msg);
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        InetSocketAddress remoteAddress = (InetSocketAddress) ctx.channel().remoteAddress();
        String clientIp = remoteAddress.getAddress().getHostAddress();
        logger.info("收到客户端 【ip：" + clientIp + "】连接,channelid:【{}】",ctx.channel().id());
        ConnectCache.createChannel(ctx,clientIp);
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        logger.error("连接异常，关闭连接");
        cause.printStackTrace();
        //清除此连接信息
        ConnectCache.removeChannelMap(ctx.channel().id());
        ctx.close();
    }


}
