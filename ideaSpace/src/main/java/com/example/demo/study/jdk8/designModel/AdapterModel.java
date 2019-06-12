package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/21
 *
 * 适配器模式：将一个类的接口变换成客户端所期待的另一种接口，
 * 从而使原本接口不匹配而无法在一起工作的两个类能够在一起工作
 *
 * 适配器模式的优点：
 * 适配器模式可以让两个没有任何关系的类在一起运行，只要适配器这个角色能搞定他们就成
 * 增加了类的透明性
 * 提高了类的复用度，源角色在原有系统中还是可以正常使用，而在目标角色中也可以当新的演员
 * 灵活性非常好，想用就用个，没用了就删除
 *
 * 适配器的使用场景：
 * 有动机修改一个已经投产中的接口时，适配器模式可能是最适合的模式
 * 比如系统扩展了，需要使用一个已有或新建的类，但这个类又不符合系统的接口
 *
 * 适配器模式的注意事项：
 * 适配器模式最好在详细设计阶段不要考虑他，
 * 它不是为了解决还处在开发阶段的问题，而是解决正在服务的项目的问题，
 * 它是一个补救模式
 */
public class AdapterModel {

    public static void main(String[] args) {
        simpleTest();
    }

    public static void simpleTest() {
        TargetImpl target = new TargetImpl();
        target.request();
        System.out.println("----------------");
        Adapter adapter = new Adapter();
        adapter.request();
    }
}

/**
 * target目标角色
 * 该角色定义把其他类转换为何种接口，也就是我们期望的接口
 *
 * adaptee源角色
 * 你想把谁转换成目标角色，这个谁就是源角色，它是已经存在的、运行良好的类或者对象
 * 经过适配器角色的包装，它会成为一个崭新的、靓丽的角色
 *
 * adapter适配器角色
 * 适配器模式的核心角色，其他两个角色都是已经存在的角色，而适配器角色是需要新建立的
 * 它的职责非常简单，把源角色转换为目标角色，通过继承或类关联的方式来转换
 */

//对象适配器：通过对象层次的关联关系进行委托
class Adapter1 implements Target {
    private Adaptee adaptee;
    public Adapter1(Adaptee adaptee) {
        this.adaptee = adaptee;
    }
    @Override
    public void request() {
        adaptee.doSomething();
    }
}



//类适配器：通过继承进行适配
interface Target {//目标角色
    //目标角色有自己的方法
    public void request();
}

class TargetImpl implements Target {//目标角色的实现类
    @Override
    public void request() {
        System.out.println("目标角色实现类的方法");
    }
}

class Adaptee {//源角色
    //原有的业务逻辑
    public void doSomething() {
        System.out.println("源角色的方法");
    }
}

class Adapter extends Adaptee implements Target {
    @Override
    public void request() {
        super.doSomething();
    }
}


