package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/25 9:43
 * @desc 插入排序
 */
public class MyInsert extends Sort {

    /**
     * 排序方法
     * @param arr
     */
    private static void sort(Integer[] arr) {
        for (int i=1;i<arr.length;i++){
            for(int j=0;j<i;j++){
                if(less(arr,i,j)){
                    //j--i-1的元素依次向后移一位；
                    for(int k=i;k>j;k--){
                        exch(arr,k,k-1);
                    }
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
