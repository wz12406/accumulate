package test;

import java.util.Scanner;

/**
 * @author Administrator
 * @date 2017/10/26 12:29
 * @desc
 */
public class T1 {

    public static Integer nextNum(int n){
        if(n==1||n==2){
            return 1;
        }
        return nextNum(n-1)+nextNum(n-2);
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("请输入参数值：");
        System.out.println("test");
        int i = scanner.nextInt();
        int num = 0;
        int j=1;
        while(true){
            num = nextNum(j);
            j++;
            if(num>i){
                break;
            }
            System.out.println(num);
        }

    }
}
