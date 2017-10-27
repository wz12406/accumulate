package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/25 9:43
 * @desc 插入排序
 */
public class MyQuickSort extends Sort {

    /**
     * 排序方法
     * @param arr
     */
    private static void sort(Integer[] arr) {
        int lo = 0;
        int hi = arr.length-1;
        quickSort(arr,lo,hi);
    }

    private static void quickSort(Integer[] arr, int lo, int hi) {
        if(lo>hi)return;
        int j = partion(arr,lo,hi);
        quickSort(arr,lo,j-1);
        quickSort(arr,j+1,hi);
    }

    private static int partion(Integer[] a, int lo, int hi) {
       int i=lo;
       int j=hi;
        while(i!=j){
            //顺序很重要，要先从右边开始找
            while(a[j]>=a[lo] && i<j)j--;
            //再找右边的
            while(a[i]<=a[lo] && i<j)i++;
            //交换两个数在数组中的位置
            if(i<j)exch(a,i,j);
        }
        exch(a,lo,i);  //最终将基准数归位
        return i;
    }

    public static boolean less(Comparable a,Comparable b){
        return a.compareTo(b)<0;
    }
    public static void main(String[] args) {
        Integer[] arr = (Integer[]) input();
        System.out.print("排序前数组：");
        printArr(arr);
        sort(arr);
        System.out.print("排序后数组：");
        printArr(arr);
    }
}
