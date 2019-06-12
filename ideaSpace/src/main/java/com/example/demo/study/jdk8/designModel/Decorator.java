package com.example.demo.study.jdk8.designModel;

/**
 * @Describe
 * @Auth duranfu
 * @Date 2019/3/14
 */
class test {
    public static void main(String[] args) {
        FouthGradeSchoolReport fouthGradeSchoolReport = new FouthGradeSchoolReport();
        HighScoreDecorator highScoreDecorator = new HighScoreDecorator(fouthGradeSchoolReport);
        highScoreDecorator.report();
    }
}

/**
 * 继承是静态地给类增加功能，装饰模式是动态地给类增加功能
 *
 * 1、装饰者模式：
 * 动态地给一个对象添加一些额外的职责，就增加功能来说，装饰者比生成子类更加灵活
 * 2、四个角色：抽象构件，具体构件，装饰角色，具体装饰角色
 * 3、装饰模式优点：
 * 装饰类和被装饰类可以独立发展，而不会相互耦合
 * 装饰模式是继承关系的一个替代方案
 * 装饰模式可以动态扩展一个实现类的功能
 * 4、装饰者模式的使用场景
 * 需要动态扩展一个类的功能，或者给一个类增加一个功能
 * 需要动态地给一个对象增加功能，这些功能可以再动态地撤销
 * 需要为一批的兄弟类行进改装或加装功能
 */


class FouthGradeSchoolReport extends SchoolReport {
    //我的成绩单
    public void report() {
//成绩单的格式是这个样子的
        System.out.println("尊敬的XXX家长:");
        System.out.println(" ......");
        System.out.println(" 语文 62 数学65 体育 98 自然 63");
        System.out.println(" .......");
        System.out.println(" 家长签名： ");
    }
    //家长签名
    public void sign() {
        System.out.println("家长签名为：");
    }
}

class SortDecorator extends Decorator {
    public SortDecorator(SchoolReport sr) {
        super(sr);
    }

    public void reportSort() {
        System.out.println("排名靠前");
    }

    @Override
    public void report() {
        super.report();
    }

}

class HighScoreDecorator extends Decorator {

    public HighScoreDecorator(SchoolReport sr) {
        super(sr);
    }

    private void reportHighScore() {
        System.out.println("先汇报高分的成绩");
    }

    @Override
    public void report() {
        this.reportHighScore();
        super.report();
    }
}

public class Decorator extends SchoolReport{
    private SchoolReport sr;
    public Decorator(SchoolReport sr) {
        this.sr = sr;
    }
    @Override
    public void report() {
        this.sr.report();
    }
    @Override
    public void sign() {
        this.sr.sign();
    }
}

abstract class SchoolReport {
    public abstract void report();//成绩单
    public abstract void sign();//成绩单需要签字
}


