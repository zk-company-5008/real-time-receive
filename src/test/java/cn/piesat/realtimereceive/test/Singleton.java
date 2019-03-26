package cn.piesat.realtimereceive.test;

/**
 * @author zk
 * @date 2019/3/25 18:51
 */
public class Singleton {
    private final static Singleton INSTANCE = new Singleton();
    private Singleton(){}
    public static Singleton getInstance(){
        return INSTANCE;
    }
}
