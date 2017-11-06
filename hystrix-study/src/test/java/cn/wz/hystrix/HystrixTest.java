package cn.wz.hystrix;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import junit.framework.TestCase;
import org.junit.Test;
import rx.Observable;
import rx.Observer;
import rx.functions.Action1;

import java.util.concurrent.Future;

/**
 * @author Administrator
 * @date 2017/11/6 10:30
 * @desc
 */
public class HystrixTest extends TestCase {

    @Test
    public void testSynchronous() throws Exception {

        //同步调用
        System.out.println(new CommandHelloWorld("World").execute());
        //异步调用
        Future<String> world = new CommandHelloWorld("World").queue();
        System.out.println(world.get());
    }

    /**
     * 以观察者模式执行
     * @throws Exception
     */
    @Test
    public void testObservable() throws Exception {
        Observable<String> fWorld = new CommandHelloWorld("World").observe();
        Observable<String> fBob = new CommandHelloWorld("Bob").observe();

        // 阻塞当前线程获取调用结果
        assertEquals("Hello World!", fWorld.toBlocking().single());
        assertEquals("Hello Bob!", fBob.toBlocking().single());

        // 非阻塞调用获取调用结果
        // - this is a verbose anonymous inner-class approach and doesn't do assertions
        fWorld.subscribe(new Observer<String>() {

            @Override
            public void onCompleted() {
                // nothing needed here
                System.out.println("----------调用完成--------------");
            }

            @Override
            public void onError(Throwable e) {
                e.printStackTrace();
            }

            @Override
            public void onNext(String v) {
                System.out.println("onNext: " + v);
            }

        });

        // non-blocking
        // - also verbose anonymous inner-class
        // - ignore errors and onCompleted signal
        fBob.subscribe(new Action1<String>() {
            @Override
            public void call(String v) {
                System.out.println("call: " + v);
            }

        });
    }

    /**
     * 测试Fallback
     * @throws Exception
     */
    @Test
    public void testFallback() throws Exception {
        assertEquals("Hello Failure World!", new CommandHelloFailure("World").execute());
        assertEquals("Hello Failure Bob!", new CommandHelloFailure("Bob").execute());
    }


    /**
     * 测试请求缓存机制
     */
    @Test
    public void testWithoutCacheHits() {
        //使用请求缓存是需要先初始化context
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            assertTrue(new CommandUsingRequestCache(2).execute());
            assertFalse(new CommandUsingRequestCache(1).execute());
            assertTrue(new CommandUsingRequestCache(0).execute());
            assertTrue(new CommandUsingRequestCache(58672).execute());
        } finally {
            context.shutdown();
        }
    }

    /**
     * 测试是否从缓存中获取的值
     */
    @Test
    public void testWithCacheHits() {
        HystrixRequestContext context = HystrixRequestContext.initializeContext();
        try {
            CommandUsingRequestCache command2a = new CommandUsingRequestCache(2);
            CommandUsingRequestCache command2b = new CommandUsingRequestCache(2);

            assertTrue(command2a.execute());
            // this is the first time we've executed this command with
            // the value of "2" so it should not be from cache
            assertFalse(command2a.isResponseFromCache());

            assertTrue(command2b.execute());
            // this is the second time we've executed this command with
            // the same value so it should return from cache
            assertTrue(command2b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
        // start a new request context
        context = HystrixRequestContext.initializeContext();
        try {
            CommandUsingRequestCache command3b = new CommandUsingRequestCache(2);
            assertTrue(command3b.execute());
            // this is a new request context so this
            // should not come from cache
            assertFalse(command3b.isResponseFromCache());
        } finally {
            context.shutdown();
        }
    }
}
