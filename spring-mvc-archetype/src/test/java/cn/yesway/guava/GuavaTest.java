package cn.yesway.guava;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.junit.Test;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Administrator
 * @date 2017/12/4 16:29
 * @desc
 */
public class GuavaTest {

    @Test
    public void test_001() throws ExecutionException {
        LoadingCache<String,Object> failedCache = CacheBuilder.newBuilder().
                softValues().maximumSize(10000)
                .build(new CacheLoader<String, Object>() {

                    @Override
                    public Object load(String s) throws Exception {
                        return new AtomicInteger(0);
                    }
                });

        failedCache.put("00",((AtomicInteger)failedCache.get("00")).incrementAndGet());
        System.out.println(failedCache.get("00"));
    }
}
