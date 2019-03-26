package cn.piesat.realtimereceive.send.impl;

import cn.piesat.realtimereceive.send.ISendToKafka;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.binding.BinderAwareChannelResolver;
import org.springframework.messaging.support.GenericMessage;
import org.springframework.stereotype.Service;

/**
 * @author zk
 * @date 2019/3/19 13:52
 */
@EnableBinding
public class SendToKafkaImpl  implements ISendToKafka {
    @Autowired
    private BinderAwareChannelResolver resolver;
    @Override
    public void sendCustomTopic(String topic, byte[] msg) {
        resolver.resolveDestination(topic).send(new GenericMessage<byte[]>(msg));
    }
}
