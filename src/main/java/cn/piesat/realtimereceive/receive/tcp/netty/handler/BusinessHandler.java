package cn.piesat.realtimereceive.receive.tcp.netty.handler;

import cn.piesat.realtimereceive.send.ISendToKafka;
import io.netty.channel.ChannelHandlerContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @date 2019/3/19 14:20
 */
@Component
public class BusinessHandler {
    @Autowired
    private ISendToKafka iSendToKafka;
    private static AtomicInteger atomicInteger = new AtomicInteger(0);
    @Async
    public void dealAndSend(ChannelHandlerContext ctx , Object msg) {
        List<byte[]> list = (List<byte[]>) msg;
        atomicInteger.addAndGet(list.size());
        System.out.println("已完成【"+ atomicInteger.get() +"】");
        /*for (byte[] bytes : list) {
            iSendToKafka.sendCustomTopic("RTSToSZ",bytes);
        }*/
    }
}
