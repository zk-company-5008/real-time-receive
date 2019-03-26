package cn.piesat.realtimereceive.receive.tcp.netty.cache;

import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author zk
 * @date 2019/3/13 14:40
 */
public class ConnectCache {

    private static Logger logger = LoggerFactory.getLogger(ConnectCache.class);

    private static Map<ChannelId,String> channelMap = new HashMap<ChannelId,String>(16);

    private static Map<ChannelId,byte[]> tempCache = new HashMap<ChannelId,byte[]>(16);

    /**
     * 创建连接信息
     * @param ctx channel通道
     * @param value 绑定连接的值
     * 还包括处理 不完整数据的临时缓冲区初始化
     */
    public static void createChannel(ChannelHandlerContext ctx , String value){
        if (channelMap.get(ctx.channel().id()) == null) {
            //绑定通道和终端序列号
            channelMap.put(ctx.channel().id(),value);
            //绑定通道和处理不完整数据的临时缓冲区
            tempCache.put(ctx.channel().id(),null);
        }
    }
    /**
     * 清除连接信息
     * @param key
     */
    public static void removeChannelMap(ChannelId key){
        logger.info("清除channelid,终端【{}】",new String(channelMap.get(key)));
        channelMap.remove(key);
        logger.info("清除通道的临时缓冲区");
        tempCache.remove(key);
    }

    /**
     * 获取通道的临时缓冲区数据
     * @param key 通道ID
     * @return
     */
    public static byte[] getTempCache(ChannelId key) {
        return tempCache.get(key);
    }
    public static void putTempCache(ChannelId key , byte[] val) {
        tempCache.put(key,val);
    }
}
