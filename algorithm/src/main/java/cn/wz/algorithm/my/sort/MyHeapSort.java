package cn.wz.algorithm.my.sort;

/**
 * @author Administrator
 * @date 2017/10/26 9:46
 * @desc
 */
public class MyHeapSort  extends Sort{

    Integer[] heap;
    int N;
    public MyHeapSort(int size){
        this.heap = new Integer[size+1];
        N=0;
    }

    public boolean hasElement(){
        return N>0;
    }
    /**
     * 元素上浮
     * @param k
     */
    public void swim(int k){
        while(k>1){
            if(less(heap,k,k/2)){
                exch(heap,k,k/2);
            }
            k = k/2;
        }
    }

    /**
     * 元素下沉
     * @param k
     */
    public void sink(int k){
        while(2*k<=N){
            int j = 2*k;
            if(j<N&&less(heap,j+1,j)){
                j=j+1;
            }
            if(less(heap,j,k)){
                exch(heap,j,k);
            }
            k=j;
        }
    }

    public void insert(int i){
        heap[N+1] = i;
        swim(N+1);
        N=N+1;
    }

    public Integer deleteMin(){
        Integer min = null;
        if(N>=1){
            exch(this.heap,1,N);
            min = this.heap[N];
            this.heap[N] = null;
            N--;
            sink(1);
        }
        return min;
    }

    public Integer[] heap(){
        return heap;
    }


    public static void main(String[] args) {
        Integer[] arr = (Integer[]) input();
        MyHeapSort heap = new MyHeapSort(arr.length);
        for(Integer i :arr){
            heap.insert(i);
        }
        while(heap.hasElement()){
            System.out.println(heap.deleteMin());
        }
    }
}
