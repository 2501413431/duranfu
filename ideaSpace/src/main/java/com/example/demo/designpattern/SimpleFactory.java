package com.example.demo.designpattern;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/2/27
 */
public class SimpleFactory {
    public static void main(String[] args) {
        Animal dog = AnimalFactory1.createAnimal();
        dog.say();
    }
}

class AnimalFactory1 {
    public static Animal createAnimal() {
        Properties p = new Properties();
        InputStream in = null;
        try {
            in = SimpleFactory.class.getResourceAsStream("/FactoryTest.properties");
            p.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        Animal a = null;
        try {
            a = (Animal)Class.forName(p.getProperty("dog")).newInstance();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return a;
    }
}


class AnimalFactory {
    public static Animal createAnimal(String type) {
        Animal a = null;
        if ("dog".equals(type)) {
            a = new Dog();
        }
        if ("Cat".equals(type)) {
            a = new Cat();
        }
        return a;
    }
}

class Dog implements Animal {
    @Override
    public void say() {
        System.out.println("汪~汪~汪~");
    }
}

class Cat implements Animal {
    @Override
    public void say() {
        System.out.println("喵~喵~");
    }
}

interface Animal {
    public void say();
}
