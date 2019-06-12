package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/19
 * 模板方法：一个抽象类公开定义了执行他的方法的方式模板，他的子类可以按需要重写方法实现
 * 但调用将以抽象类中定义的方式进行
 *
 * 定义一个操作中的算法的框架，而将一些步骤延迟到子类中，
 * 使得子类可以不改变一个算法的结构即可重定义该算法的某些特定步骤
 */
public class TemplateModel {
    public static void main(String[] args) {
        HumerModel h1 = new Humer1();
        h1.run();
    }
}

abstract class HumerModel {
    protected abstract void start();
    protected abstract void stop();
    protected abstract void alarm();
    protected abstract void engineBoom();
    public final void run() {
        this.start();
        this.engineBoom();
        this.alarm();
        this.stop();
    }
}

class Humer1 extends HumerModel {
    protected  void start() {
        System.out.println("悍马1号启动");
    }
    protected  void stop() {
        System.out.println("悍马1号停车");
    }
    protected  void alarm() {
        System.out.println("悍马1号鸣笛");
    }
    protected  void engineBoom() {
        System.out.println("悍马1号开始跑");
    }
}

