package cn.piesat.realtimereceive.receive.tcp.netty.handler;

import cn.piesat.realtimereceive.receive.tcp.netty.cache.ConnectCache;
import cn.piesat.realtimereceive.receive.util.CheckUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.util.CharsetUtil;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @date 2019/3/13 20:08
 */
@Component
@Scope("prototype")
public class Test {

    private Logger logger = LoggerFactory.getLogger(Test.class);


    private AtomicInteger atomicInteger = new AtomicInteger(0);

//    @Async
    public void doDeal(ChannelHandlerContext ctx , Object msg){

        List<byte[]> list = (List<byte[]>) msg;
        atomicInteger.addAndGet(list.size());
        System.out.println("已完成【"+ atomicInteger.get() +"】");

    }

    public String cutString(String data , String startSign , String endSign){
        String str = "";
        int startIndex = data.indexOf(startSign);
        int endIndex = data.indexOf(endSign);
        if(startIndex != -1 && endIndex != -1) {
            str = data.substring(startIndex + 1,endIndex);
        }
        return str;
    }

    public static void main(String[] args) {
        System.out.println("\033[;31m数据过短\033[0m");
    }
}
