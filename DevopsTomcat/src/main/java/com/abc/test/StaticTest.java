package com.abc.test;

public class StaticTest {

    static int age = 1;

    static {
        System.out.println("这是静态代码块");
    }

    int age1 = 2;

    {
        System.out.println("这是普通代码块" + age1);
    }

    public StaticTest() {
        System.out.println("这是构造方法");
    }

    public static void show() {
        System.out.println("这是静态方法");
    }

    public static void main(String[] args) {
        System.out.println("age:" + age);
        StaticTest.show();
        StaticTest t = new StaticTest();
        StaticTest.show();
        t.fun();
        System.out.println("t.age1:" + t.age1);

        String a = Boolean.toString(true);
        System.out.println(a);
    }

    public void fun() {
        System.out.println("这是普通方法");
    }

}