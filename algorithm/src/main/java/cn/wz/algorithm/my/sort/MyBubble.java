package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/25 9:43
 * @desc 插入排序
 */
public class MyBubble extends Sort {

    /**
     * 排序方法
     * @param arr
     */
    private static void sort(Integer[] arr) {
        for(int i=0;i<arr.length;i++){
           for (int j=arr.length-1;j>i;j--){
               if(less(arr,j,j-1)){
                   exch(arr,j,j-1);
               }
           }
        }
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
