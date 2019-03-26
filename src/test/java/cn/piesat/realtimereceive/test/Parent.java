package cn.piesat.realtimereceive.test;

public class Parent {
    private static String name = method2();
    public Parent() {
        System.out.println("构造1");
    }
    static{
        System.out.println("static 1");
    }
    public void method1() {
        System.out.println("i am parent method!");
    }
    public static String method2() {
        System.out.println("i am parent static method!");
        return "parent";
    }
}
class Child extends Parent {
    static{
        System.out.println("static 2");
    }
    private static String name=method2();
    public Child() {
        System.out.println("构造2");
    }
    public void method1() {
        System.out.println("i am child method!");
    }

    public static String method2() {
        System.out.println("i am child static method!");
        return "child";
    }
}
class Domain {
    public static void main(String[] args) {
        Parent parent = new Parent();
        Child child = new Child();
        child.method1();
    }
}