package cn.yesway.thread;

/**
 * @author Administrator
 * @date 2017/12/26 11:23
 * @desc 测试volatile修饰的变量不是线程安全的
 */
public class VolatileTest {

   // public static volatile AtomicInteger race =new AtomicInteger(0);
    public static volatile int race =0;

    public static void increase(){
        race++;
    }

    private  static final  int THREADS_COUNT = 20;

    public static void main(String[] args){
        Thread[] threads = new Thread[THREADS_COUNT];
        for(int i = 0;i<THREADS_COUNT;i++){
            threads[i] = new Thread(new Runnable() {
                public void run() {
                    for(int i=0;i<10000;i++){
                        increase();
                    }
                }
            });
            threads[i].start();
        }

        while (Thread.activeCount()>1)Thread.yield();

        System.out.println(race);
    }
}
