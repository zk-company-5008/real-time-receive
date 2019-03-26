package cn.piesat.realtimereceive.Listener;

import cn.piesat.realtimereceive.receive.tcp.netty.server.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

/**
 * @author zk
 * @date 2019/3/13 16:12
 */
@WebListener
public class NettyServerListener implements ServletContextListener {

    @Autowired
    private NettyServer nettyServer;

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        new Thread(()->{
            nettyServer.startServer();
        }).start();
    }
}
