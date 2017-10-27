package test;

import org.junit.Test;

/**
 * @author Administrator
 * @date 2017/9/28 11:34
 * @desc
 */
public class CommonTest {

    @Test
    public void test_001(){
        String s = "Hello World";
        s.toUpperCase();
        s.substring(6,11);
        System.out.println(s);
    }
    @Test
    public void test_002(){
        String s1 = "Hello";
        String s2 = s1;
        s1="World";
        System.out.println(s1);  //world
        System.out.println(s2);  //hello
    }

    @Test
    public void test_003(){
        String s1 = "Hello";
        String s2 = s1;
        s1="World";
        System.out.println(s1);  //world
        System.out.println(s2);  //hello
    }
}
