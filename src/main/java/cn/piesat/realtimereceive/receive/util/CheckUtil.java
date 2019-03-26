package cn.piesat.realtimereceive.receive.util;

/**
 * @author zk
 * @date 2019/3/13 14:04
 * 校验工具类
 */
public class CheckUtil {

    /**
     * 异或校验
     * @param bytes 待校验数据
     * @return
     */
    public static synchronized boolean checkXOR(byte[] bytes){
        boolean flag = false;
        int temp = bytes[0];
        for (int i = 1; i < bytes.length; i++) {
            temp ^= bytes[i];
        }
        if (temp == 0) {
            flag = true;
        }
        return flag;
    }
}
