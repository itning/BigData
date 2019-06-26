package top.itning.bigdata.guava.caches;

import com.google.common.cache.*;
import org.junit.Test;

import javax.annotation.ParametersAreNonnullByDefault;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

/**
 * 缓存
 *
 * @author itning
 */
public class CachesTest {
    @Test
    public void test() throws ExecutionException {
        LoadingCache<String, String> graphs = CacheBuilder.newBuilder()
                .maximumSize(1000)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<String, String>() {
                    @Override
                    @ParametersAreNonnullByDefault
                    public void onRemoval(RemovalNotification<String, String> notification) {
                        System.out.println(notification);
                    }
                })
                .build(new CacheLoader<String, String>() {
                    @Override
                    @ParametersAreNonnullByDefault
                    public String load(String key) throws Exception {
                        return "k:" + key;
                    }
                });

        Cache<String, String> cache = CacheBuilder.newBuilder()
                .build();
        String kk = graphs.get("kk");
        System.out.println(kk);
    }
}
