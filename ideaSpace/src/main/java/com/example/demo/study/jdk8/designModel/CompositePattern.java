package com.example.demo.study.jdk8.designModel;

import java.util.ArrayList;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/22
 *
 * 组合模式也叫合成模式：
 * 将对象组合成树形结构以表示部分整体的层次结构，
 * 使得用户对单个对象和组合对象的使用具有一致性
 *
 * 只要是树形结构，就要考虑用组合模式，只要是体现局部和整体关系的时候，
 * 而且这种关系还可能比较深，考虑用组合模式
 */
public class CompositePattern {

}


class Client {
    public void test() {
        //创建一个根节点
        Composite root = new Composite();
        root.doSomething();
        //树枝
        Composite branch = new Composite();
        //叶子
        Leaf leaf = new Leaf();
        //建立整体
        root.add(branch);
        branch.add(leaf);
    }

    public void display(Composite root) {
        for (Component c: root.getChildren()) {
            if (c instanceof Leaf) {
                c.doSomething();
            } else {
                display((Composite) c);
            }
        }
    }
}

abstract class Component {
    //个体和整体都有的共享
    public void doSomething() {
        //编写业务逻辑
    }
}

class Composite extends Component {
    //构件容器
    private ArrayList<Component> componentArrayList = new ArrayList<Component>();
    //增加一个叶子构件或树枝构件
    public void add(Component component) {
        this.componentArrayList.add(component);
    }
    //删除一个叶子构件或树枝构件
    public void remove(Component component) {
        this.componentArrayList.remove(component);
    }
    //获得分支下的所有叶子构件和树枝构件
    public ArrayList<Component> getChildren() {
        return this.componentArrayList;
    }
}

class Leaf extends Component {
    @Override
    public void doSomething() {
        //
    }
}
