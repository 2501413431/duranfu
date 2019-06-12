package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2018/12/12
 */
public class Singleton {
    private volatile static Singleton instance;
    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }
}

class SingleTon1 {
    private static  class SingleTon1Handler {
        private static SingleTon1 instance = new SingleTon1();
    }
    public static SingleTon1 getInstance() {
        return  SingleTon1Handler.instance;
    }

}

/**
 * 先获取本线程的该实例，如果没有，就创建该线程独有的ErrorContext
 */
class ErrorContext {
    private static final ThreadLocal<ErrorContext> LOCAL= new ThreadLocal<ErrorContext>();
    private ErrorContext() {}
    public static ErrorContext getInstance() {
        ErrorContext errorContext = LOCAL.get();
        if (errorContext == null) {
            errorContext = new ErrorContext();
            LOCAL.set(errorContext);
        }
        return errorContext;
    }
}

