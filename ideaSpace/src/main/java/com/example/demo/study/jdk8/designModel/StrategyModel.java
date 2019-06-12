package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/18
 *
 * 策略模式:定义一组算法，将每个算法都封装起来，并且使他们之间可以互换
 * 采用了面向对象的继承和多态机制
 * 策略模式的优点：
 *      1、算法可以自由切换
 *      2、避免使用多重条件判断
 *      3、扩展性良好，在现有项目中增加一个策略太容易了，只要实现接口就可以了。
 *  策略模式的缺点：
 *      1、策略类数量增多，每一个策略都是一个类，复用的可能性小，类数量增多
 *      2、所有的策略类都需要对外暴露
 *   策略模式的使用场景：
 *      1、多个类只有在算法或行为上稍有不同的场景
 *      2、算法需要自由切换的场景
 *      3、需要屏蔽算法规则的场景
 *    策略模式的注意事项：
 *      如果系统中的一个策略家族的具体策略数量超过4个，则需要考虑使用混合模式，解决策略类膨胀和对外暴露问题
 *    策略模式是一个非常简单的模式，它在项目中使用得非常多，但他单独使用的地方就比较少了，因为他有致命缺陷。
 *    在实际项目中一般通过工厂模式来实现策略类的声明。例如混合模式
 */
public class StrategyModel {
    public static void main(String[] args) {
        String version = "3.1.0";
        String now = "3.0.5";
        int i = now.compareTo(version);
        System.out.println(i);
    }
}

interface Strategy {
    public void doSomething();
}

class Strategy1 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("strategy1的具体方法");
    }
}

class Strategy2 implements Strategy {
    @Override
    public void doSomething() {
        System.out.println("strategy2的具体方法");
    }
}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public void doSomething() {
        this.strategy.doSomething();
    }
}

//枚举策略
enum  Calculator {
    ADD("+") {
        public int exec(int a, int b) {
            return a + b;
        }
    },
    SUB("-") {
        public int exec(int a, int b) {
            return  a- b;
        }
    };

    String value = "";
    private Calculator(String _value) {
        this.value = _value;
    }

    public String getValue() {
        return this.value;
    }

    public abstract int exec(int a, int b);
}