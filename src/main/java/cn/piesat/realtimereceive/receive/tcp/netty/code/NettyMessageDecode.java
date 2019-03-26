package cn.piesat.realtimereceive.receive.tcp.netty.code;

import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToMessageDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author zk
 * @date 2018/10/7 13:32
 */
@Component
@Scope("prototype")
@ChannelHandler.Sharable
public class NettyMessageDecode extends MessageToMessageDecoder<Object> {

    private Logger logger = LoggerFactory.getLogger(NettyMessageDecode.class);

    @Autowired
    private UnpackByte unpackByte;

    @Override
    protected void decode(ChannelHandlerContext channelHandlerContext, Object o, List<Object> list) throws Exception {
        List<byte[]> messages = unpackByte.doByteDeal(channelHandlerContext,o);
        list.add(messages);
    }
}
