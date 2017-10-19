package cn.wz.algorithm.my.sort;

import cn.wz.algorithm.stdlib.StdOut;

/*************************************************************************
 * 插入排序实现
 *  
 *************************************************************************/

public class Insertion {

    public static void sort(Comparable[] a) {
        for(int i=1;i<a.length;i++){
           for(int j=i;j>0&&less(a[j],a[j-1]);j--){
                exch(a,j,j-1);
           }
        }
    }


   /***********************************************************************
    *  比较方法
    ***********************************************************************/
    
    // is v < w ?
    private static boolean less(Comparable v, Comparable w) {
        return (v.compareTo(w) < 0);
    }

    /**
     * 元素交换
     * @param a
     * @param i
     * @param j
     */
    private static void exch(Object[] a, int i, int j) {
        Object swap = a[i];
        a[i] = a[j];
        a[j] = swap;
    }


   /***********************************************************************
    *  Check if array is sorted - useful for debugging
    ***********************************************************************/
    private static boolean isSorted(Comparable[] a) {
        return isSorted(a, 0, a.length - 1);
    }

    private static boolean isSorted(Comparable[] a, int lo, int hi) {
        for (int i = lo + 1; i <= hi; i++)
            if (less(a[i], a[i-1])) return false;
        return true;
    }

    // print array to standard output
    private static void show(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            StdOut.println(a[i]);
        }
    }

    // Read strings from standard input, sort them, and print.
    public static void main(String[] args) {
        String[] a = {"S","a","X","Z","Y"};
        Integer[] b = {1,5,2,4,3};
        Insertion.sort(b);
        show(b);
    }
}
