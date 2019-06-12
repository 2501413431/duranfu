package com.example.demo.designpattern;

import sun.applet.Main;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/2/27
 */
public class AbstractFactory {
}

/**
 * 如果产品全部属于同一个等级结构（即同一个接口或抽象类），则属于工厂方法模式
 * 如果产品来自多个等级结构（即多个不同的接口或抽象类），则属于抽象工厂模式
 */

interface AbstractFactorya {
    public Cpu createCpu();
    public Mainboard createMainboard();
}

class InterFactory implements AbstractFactorya {

    @Override
    public Cpu createCpu() {
        return new IntelCpu();
    }

    @Override
    public Mainboard createMainboard() {
        return new IntelMainboard();
    }
}

class AmdFactory implements AbstractFactorya {

    @Override
    public Cpu createCpu() {
        return new AmdCpu();
    }

    @Override
    public Mainboard createMainboard() {
        return new AmdMainboard();
    }
}


interface Mainboard {
    public void installCpu();
}

class IntelMainboard implements Mainboard {
    @Override
    public void installCpu() {
        System.out.println("因特尔主板");
    }
}

class AmdMainboard implements Mainboard {
    @Override
    public void installCpu() {
        System.out.println("AMD主板");
    }
}

interface Cpu {
    public void calculate();
}

class IntelCpu implements Cpu {
    @Override
    public void calculate() {
        System.out.println("因特尔CPU");
    }
}

class AmdCpu implements Cpu {
    @Override
    public void calculate() {
        System.out.println("AMD的CPU");
    }
}