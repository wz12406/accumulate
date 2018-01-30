package cn.yesway.test;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

/**
 * @author Administrator
 * @date 2017/12/19 15:26
 * @desc
 */
public class MethodHandleTest {

    static  class ClassA{
        public  void println(String s){
            System.out.println("---------"+s);
        }
    }

    public static void main(String[] args) throws Throwable{
        Object obj = System.currentTimeMillis()%2==0?System.out:new ClassA();
        getPrintlnMH(obj).invokeExact("icyfenix");
    }

    public static MethodHandle getPrintlnMH(Object reveiver) throws Exception {

        MethodType mt = MethodType.methodType(void.class,String.class);
        return MethodHandles.lookup().findVirtual(reveiver.getClass(),"println",mt).bindTo(reveiver);
    }
}
