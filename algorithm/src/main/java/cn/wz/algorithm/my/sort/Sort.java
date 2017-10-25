package cn.wz.algorithm.my.sort;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * @author Administrator
 * @date 2017/10/25 9:26
 * @desc
 */
public class Sort {

    /**
     * 比较方法
     * @param a
     * @param b
     * @return
     */
    public static boolean less(Comparable[] arr, Integer a, Integer b){
        return arr[a].compareTo(arr[b])<0;
    }


    /**
     * 交换元素中两个元素
     * @param arr 数组
     * @param a index1
     * @param b index2
     * @return
     */
    public static void exch(Comparable[] arr,int a,int b){
        Comparable temp = arr[a];
        arr[a] = arr[b];
        arr[b] = temp;
        System.out.println("交换"+a+"与"+b);
    }


    /**
     * 打印数组
     * @param arr
     */
    public static void  printArr(Comparable[] arr){
        for (Comparable com:arr){
            System.out.print(com+" ");
        }
        System.out.println("");
        System.out.println("--------------------");
    }

    /**
     * 读取输入
     * @return
     */
    public static Comparable[] input(){
        Scanner scanner = new Scanner(Insert.class.getClassLoader().getResourceAsStream("tinysort.txt"));
        int count = 0;
        List<Integer> arrlist = new ArrayList<>();
        while (scanner.hasNextInt()){
            arrlist.add(scanner.nextInt());
            count++;
        }
        scanner.reset();
        Integer[] arr = new Integer[arrlist.size()];
        arrlist.toArray(arr);
        return arr;
    }

}
