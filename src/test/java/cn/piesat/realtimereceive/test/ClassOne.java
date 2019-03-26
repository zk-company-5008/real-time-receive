package cn.piesat.realtimereceive.test;

/**
 * @author zk
 * @date 2019/3/25 10:31
 */
public class ClassOne {
    public long methodOne() {
        return 1;
    }

}
class ClassTwo extends ClassOne {
    public long methodOne() {
        return 1;
    }

    public static void main(String[] args) {
        ClassTwo classOne = new ClassTwo();
        classOne.methodOne();
    }
}
