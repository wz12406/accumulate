package cn.yesway.test;

/**
 * @author Administrator
 * @date 2017/12/19 11:38
 * @desc
 */
public class GcTest {

    static abstract class Human{

    }

    static class Man extends Human{

    }

    static class Weman extends  Human{

    }

    public void sayHello(Human guy){
        System.out.println("hello，guy");
    }
    public void sayHello(Man guy){
        System.out.println("hello，man");
    }
    public void sayHello(Weman guy){
        System.out.println("hello，weman");
    }

    public static void main(String[] args) {
        Human man= new Man();
        Human weman = new Weman();
        GcTest gt = new GcTest();
        gt.sayHello(man);
        gt.sayHello(weman);

    }
}
