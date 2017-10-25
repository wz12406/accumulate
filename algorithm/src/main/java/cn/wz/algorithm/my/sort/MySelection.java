package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/25 9:43
 * @desc 选择排序
 */
public class MySelection extends Sort {

    /**
     * 排序方法
     * @param arr
     */
    private static void sort(Integer[] arr) {
        for(int i=0;i<arr.length;i++){
            int min = arr[i];
            for(int j=i+1;j<arr.length;j++){
                if(less(arr,j,i)){
                    exch(arr,i,j);
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
