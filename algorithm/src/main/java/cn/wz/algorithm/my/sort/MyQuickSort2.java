package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/27 9:48
 * @desc
 */
public class MyQuickSort2 extends Sort {

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

    private static int partion(Integer[] arr, int lo, int hi) {
        int i = lo;
        int j = hi;
        Integer temp = arr[lo];
        while(i!=j){
            //顺序很重要，要先从右边开始找 为什么呢？因为最后确定基准位置时候右边的都小于基准值，如6 1 2 7 9
            //以6为基准 从先从左向右探测，定位在7上，再从右向左探测，由于i<j也会定位在7上，然后交换6和7无法满足
            //右边的都小于左边的要求
            // 参考http://blog.csdn.net/w282529350/article/details/50982650
            while (arr[j]>=temp&&i<j){
                j--;
            }
            while (arr[i]<=temp&&i<j){
                i++;
            }
            if(i<j){
                exch(arr,i,j);
            }
        }
        exch(arr,i,lo);
        return i;
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
