package com.example.demo.study.jdk8.designModel;

import jdk.internal.org.objectweb.asm.Handle;

import java.util.ArrayList;
import java.util.Random;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/18
 *
 * 责任链模式
 * 将请求的接收者对象连成一条链，然后在这一条链上传递请求，直到有一个接收者处理这个请求，
 * 通过这种方式避免了请求者和接收者之间的耦合。
 * https://www.jianshu.com/p/75946acd80e3
 */
public class ChainOfResponsibility {
    public static void main(String[] args) {
        testCase();
    }

    public static void testCase() {
        int input = 8;
        OneCase oneCase = new OneCase(1 == input);
        TwoCase twoCase = new TwoCase(2 == input);
        DefaultCase defaultCase = new DefaultCase(true);
        oneCase.setNextCase(twoCase);
        twoCase.setNextCase(defaultCase);
        oneCase.handleRequest();
    }

    public static void testWomen() {
        Random random = new Random();
        ArrayList<IWomen> arrayList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            arrayList.add(new Women(random.nextInt(4),"出去逛街"));
        }
        //定义三个请示对象
        Handler father = new Father();
        Handler husband = new Husband();
        Handler son = new Son();
        //设置请示顺序
        father.setNext(husband);
        husband.setNext(son);
        for (IWomen women: arrayList) {
            father.HandleMessage(women);
        }
    }
}

/**
 * 场景：给定一个输入值，根据输入值执行不同逻辑
 * 如果每个分支里的逻辑比较简单，可以用if,case,如果逻辑复杂，假设每个case大概要100行代码处理，有10个case,
 */

abstract class BaseCase {
    //为true表示自己可以处理该case
    private boolean isConsume;
    public BaseCase(boolean isConsume) {
        this.isConsume = isConsume;
    }
    //下一个责任链节点
    private BaseCase nextCase;
    public void setNextCase(BaseCase baseCase) {
        this.nextCase = baseCase;
    }
    public void handleRequest() {
        if (isConsume) {
            //如果当前节点可以处理，直接处理
            doSomething();
        } else {
            //如果当前节点不能处理，并且有下个节点，交由下个节点处理
            if (nextCase !=  null) {
                nextCase.handleRequest();
            }
        }
    }
    abstract protected void doSomething();
}

class OneCase extends BaseCase {
    public OneCase(boolean isConsume) {
        super(isConsume);
    }
    @Override
    protected void doSomething() {
        System.out.println(getClass().getName());
    }
}

class TwoCase extends BaseCase {
    public TwoCase(boolean isConsume) {
        super(isConsume);
    }
    @Override
    protected void doSomething() {
        System.out.println(getClass().getName());
    }
}

class DefaultCase extends BaseCase {
    public DefaultCase(boolean isConsume) {
        super(isConsume);
    }
    @Override
    protected void doSomething() {
        System.out.println(getClass().getName());
    }
}





interface IWomen {
    public int getType();//个人情况
    public String getRequest();//请示
}

class Women implements IWomen {
    /*
    * 通过一个int类型的参数来描述妇女的个人状况
    * 1--未出嫁
    * 2--出嫁
    * 3--夫死
    */
    private int type = 0;
    //妇女的请示
    private String request = "";

    public Women(int type, String request) {
        this.type = type;
        this.request = request;
    }

    @Override
    public int getType() {
        return this.type;
    }

    @Override
    public String getRequest() {
        return this.request;
    }
}



class Father extends Handler {
    //父亲只处理女儿的请求
    public Father() {
        super(Handler.FATHER_LEVEL_REQUEST);
    }
    @Override
    protected void response(IWomen women) {
        System.out.println("------女儿向父亲请示-----");
        System.out.println(women.getRequest());
        System.out.println("父亲给的答复是：同意\n");
    }
}

class Husband extends Handler {
    //丈夫只处理妻子的请求
    public Husband() {
        super(Handler.HUSBAND_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("--------妻子向丈夫请示-------");
        System.out.println(women.getRequest());
        System.out.println("丈夫的答复是：同意\n");
    }
}

class Son extends Handler {
    //丈夫只处理母亲的请求
    public Son() {
        super(Handler.SON_LEVEL_REQUEST);
    }

    @Override
    protected void response(IWomen women) {
        System.out.println("--------母亲向儿子请示-------");
        System.out.println(women.getRequest());
        System.out.println("儿子的答复是：同意\n");
    }
}

abstract class Handler {
    public final static int FATHER_LEVEL_REQUEST = 1;
    public final static int HUSBAND_LEVEL_REQUEST = 2;
    public final static int SON_LEVEL_REQUEST = 3;

    //能处理的级别
    private int level = 0;
    //责任传递，下一个责任人是谁
    private Handler nextHandler;

    //每个类都要说明一下自己能处理那些请求
    public Handler(int level) {
        this.level = level;
    }

    public final void HandleMessage(IWomen women) {
        if (women.getType() == this.level) {
            this.response(women);
        } else {
            if (this.nextHandler != null) {
                this.nextHandler.HandleMessage(women);
            } else {
                System.out.println("没有地方请示了，按不");
            }
        }
    }

    public void setNext(Handler handler) {
        this.nextHandler = handler;
    }

    protected abstract void response(IWomen women);

}


