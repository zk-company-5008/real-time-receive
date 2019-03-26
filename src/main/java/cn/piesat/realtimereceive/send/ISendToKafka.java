package cn.piesat.realtimereceive.send;

/**
 * @author zk
 * @date 2019/3/19 13:49
 */
public interface ISendToKafka {
    /**
     * 发送自定义主题
     * @param topic 主题名
     * @param msg   消息
     */
    void sendCustomTopic(String topic , byte[] msg);
}
