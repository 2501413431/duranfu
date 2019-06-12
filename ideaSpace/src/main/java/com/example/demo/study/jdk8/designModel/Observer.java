package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/25
 *
 * 观察着模式：又叫发布订阅者模式
 * 定义对象间一种一对多的依赖关系，使得每当一个对象改变状态，
 * 则所有依赖于它的对象都会得到通知并被自动更新
 */
public class Observer {
    public static void main(String[] args) throws Exception {
        LiSi liSi = new LiSi();
        HanFeiZi hanFeiZi = new HanFeiZi();
        Watch watchBreakfast = new Watch(hanFeiZi, liSi, "breakfast");
        watchBreakfast.start();
        Watch watchFun = new Watch(hanFeiZi, liSi, "fun");
        watchFun.start();
        Thread.sleep(2000);
        hanFeiZi.haveBreakfast();
        Thread.sleep(2000);
        hanFeiZi.haveFun();
    }
}

class Watch extends Thread {
    private HanFeiZi hanFeiZi;
    private LiSi liSi;
    private String type;

    public Watch(HanFeiZi hanFeiZi, LiSi liSi, String type) {
        this.hanFeiZi = hanFeiZi;
        this.liSi = liSi;
        this.type = type;
    }

    @Override
    public void run() {
        while (true) {
            if (this.type.equals("breakfast")) {
                if (this.hanFeiZi.isHavingBreakfast()) {
                    this.liSi.update("韩非子在吃饭");
                    //重置状态，继续吃饭
                    this.hanFeiZi.setHavingBreakfast(false);
                }
            }  else {
                if (this.hanFeiZi.isHavingFun()) {
                    this.liSi.update("韩非子在娱乐");
                    this.hanFeiZi.setHavingFun(false);
                }
            }
        }
    }
}

class LiSi implements ILiSi {
    @Override
    public void update(String context) {
        System.out.println("李斯：观察到韩非子活动，开始向老板汇报");
        this.reportToQinShiHuang(context);
        System.out.println("李斯：汇报完毕。。。\n");
    }

    private void reportToQinShiHuang(String reportContext) {
        System.out.println("李斯：报告秦老板，韩非子有活动了--->" + reportContext);
    }
}

//抽象观察者
interface ILiSi {
    //发现别人有动静，自己也要行动起来
    public void update(String context);
}

//被观察者
interface IHanFeiZi {
    //吃早饭
    public void haveBreakfast();
    //娱乐活动
    public void haveFun();
}

//具体的被观察者
//class HanFeiZi implements IHanFeiZi {
//    private LiSi liSi;
//
//    @Override
//    public void haveBreakfast() {
//        System.out.println("韩非子：开始吃饭");
//        this.liSi.update("韩非子在吃饭");
//    }
//
//    @Override
//    public void haveFun() {
//        System.out.println("韩非子：开始娱乐");
//        this.liSi.update("韩非子在娱乐了");
//    }
//}
class HanFeiZi implements IHanFeiZi {
    private boolean isHavingBreakfast = false;
    private boolean isHavingFun = false;

    @Override
    public void haveBreakfast() {
        System.out.println("韩非子：开始吃饭");
        this.isHavingBreakfast = true;
    }

    @Override
    public void haveFun() {
        System.out.println("韩非子：开始娱乐");
        this.isHavingFun = true;
    }

    //geter seter方法
    public boolean isHavingBreakfast() {
        return isHavingBreakfast;
    }

    public boolean isHavingFun() {
        return isHavingFun;
    }

    public void setHavingBreakfast(boolean havingBreakfast) {
        isHavingBreakfast = havingBreakfast;
    }

    public void setHavingFun(boolean havingFun) {
        isHavingFun = havingFun;
    }
}