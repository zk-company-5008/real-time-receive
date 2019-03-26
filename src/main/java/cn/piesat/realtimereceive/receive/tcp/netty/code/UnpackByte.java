package cn.piesat.realtimereceive.receive.tcp.netty.code;

import cn.piesat.realtimereceive.receive.tcp.netty.cache.ConnectCache;
import cn.piesat.realtimereceive.receive.util.CheckUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import io.netty.util.ReferenceCountUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zk
 * @date 2018/10/7 13:34
 * 解包：将粘包、半包数据拆分或补全成完整包
 */
@Component
@Scope("prototype")
public class UnpackByte {

    private static Logger logger = LoggerFactory.getLogger(UnpackByte.class);

    private static AtomicInteger atomicInteger = new AtomicInteger(0);

    public List<byte[]> doByteDeal(ChannelHandlerContext ctx, Object obj){
        /**
         * 解决普通socket客户端发到netty服务端，数据格式问题
         */
        ByteBuf byteBuf = (ByteBuf) obj;
        byte[] bytes = new byte[byteBuf.readableBytes()];
        byteBuf.readBytes(bytes);
        //在编解码器内不用清理byteBuf，暂时不知道原因
//        System.out.println(ReferenceCountUtil.release(byteBuf));
        List<byte[]> list = new ArrayList<byte[]>();
        byte[] temp = ConnectCache.getTempCache(ctx.channel().id());
        //字节流
        for (int i=0;i<bytes.length;i++) {
            if (temp != null) {
                /*
                 * 缓冲区数据与后来的数据拼接
                 */
                byte[] bytesTemp = bytes;
                bytes = new byte[temp.length + bytesTemp.length];
                System.arraycopy(temp,0,bytes,0,temp.length);
                System.arraycopy(bytesTemp,0,bytes,temp.length,bytesTemp.length);

                i = -1;
                //清空临时缓冲区
                ConnectCache.putTempCache(ctx.channel().id(),null);
                temp = null;
                continue;
            }
            String headSync = Character.toString((char) (bytes[i] & 0xFF));
            //找到数据头
            if ("$".equals(headSync) && bytes.length >= 7 + i) {
                //数据总长度
                int dataLength = (bytes[5 + i] & 0xFF) * 256 + (bytes[6 + i] & 0xFF);
                if (bytes.length - i - 1 < dataLength) {
                    //初始化临时缓冲区
                    temp = new byte[bytes.length - i];
                    //缓冲区放入带头信息数据
                    System.arraycopy(bytes,i,temp,0,bytes.length - i);
                    ConnectCache.putTempCache(ctx.channel().id(),temp);
                    //数据不完整，直接跳出循环
                    break;
                }
                //异或校验
                byte[] checkByte = new byte[dataLength + 1];
                System.arraycopy(bytes,i,checkByte,0,dataLength + 1);
                boolean flag = CheckUtil.checkXOR(checkByte);

                if (!flag) {
                    continue;
                }
                list.add(checkByte);
                //偏移（此条数据长度+偏移量）- for循环的自增1
                i += checkByte.length - 1;
            } else if ("$".equals(headSync) && bytes.length < 7 + i) {
                //数据包含头信息但是不包含“长度”位
                temp = new byte[bytes.length - i];
                System.arraycopy(bytes,i,temp,0,bytes.length - i);
                ConnectCache.putTempCache(ctx.channel().id(),temp);
                break;
            } else {
                //缓冲区无数据，数据无头信息，不做处理直接丢弃
            }
        }
        return list;
    }
}