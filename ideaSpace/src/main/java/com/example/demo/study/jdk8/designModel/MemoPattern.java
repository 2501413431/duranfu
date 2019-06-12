package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/26
 * 备忘录模式；在不破坏封装性的前提下，捕获一个对象的内部状态 ，并在该对象之外保存这个状态
 * 这样以后就可将该对象恢复到原先保存的状态
 *
 * 使用场景：
 * 1、需要保存和恢复数据的相关状态场景
 * 2、提供一个可回滚的操作
 * 3、数据库连接的事务管理就是用的备忘录模式
 */
public class MemoPattern {
    public static void main(String[] args) {
        test2();
    }

    public static void test2() {
        //定义发起人
        Originator2 originator1 = new Originator2();
        //建立初始状态
        originator1.setState("初始状态。。。");
        System.out.println("初始状态是：" + originator1.getState());
        //建立备份
        originator1.createMemento();
        //修改状态
        originator1.setState("修改后的状态。。。");
        System.out.println("修改后的状态是：" + originator1.getState());
        //恢复原有状态
        originator1.restoreMemento();
        System.out.println("恢复后的状态是：" + originator1.getState());
    }

    public static void test1() {
        //定义发起人
        Originator originator = new Originator();
        //定义备忘录管理员
        Caretaker caretaker = new Caretaker();
        //创建一个备忘录
        caretaker.setMemento(originator.createMemento());
        //恢复一个备忘录
        originator.restoreMemento(caretaker.getMemento());
    }
}

//克隆模式的备忘录
class Originator1 implements Cloneable {
    private Originator1 backup;

    //内部状态
    private String state = "";

    //创建备忘录
    public void createMemento() {
        this.backup = this.clone();
    }

    //恢复备忘录
    public void restoreMemento() {
        this.setState(this.backup.getState());
    }

    //克隆当前对象
    @Override
    protected Originator1 clone() {
        try {
            return (Originator1)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return  null;
        }
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}

class Originator2 implements Cloneable {
    private String state = "";

    private Originator2 backup;

    public void createMemento() {
        this.backup = this.clone();
    }

    public void restoreMemento() {
        this.state = backup.state;
    }

    @Override
    protected Originator2 clone() {
        try {
            return (Originator2)super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
            return null;
        }
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}




class Caretaker {
    //备忘录对象
    private Memento memento;

    public Memento getMemento() {
        return memento;
    }

    public void setMemento(Memento memento) {
        this.memento = memento;
    }
}

//发起人角色
class Originator {
    //内部状态
    private String state = "";
    //创建一个备忘录
    public Memento createMemento() {
        return new Memento(this.state);
    }
    //恢复一个备忘录
    public void restoreMemento(Memento memento) {
        this.setState(memento.getState());
    }

    public String getState() {
        return state;
    }
    public void setState(String state) {
        this.state = state;
    }
}

class Memento {
    //发起人的内部状态
    private String state = "";
    //构造函数传递参数

    public Memento(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}