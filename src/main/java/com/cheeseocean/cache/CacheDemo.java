package com.cheeseocean.cache;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheManagerUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.Element;

public class CacheDemo {

    public static void main(String[] args) throws IOException, InterruptedException {
//        ApplicationContext context = new AnnotationConfigApplicationContext(CacheConfig.class);
//        CacheConfig demo = context.getBean(CacheConfig.class);
//        demo.putUser();
//        System.out.println(demo.getUser());
        net.sf.ehcache.CacheManager cacheManager = net.sf.ehcache.CacheManager.create(new ClassPathResource("ehcache.xml").getFile().getAbsolutePath());
        net.sf.ehcache.Cache cache = cacheManager.getCache("user");
        Thread t1 = new Thread(() -> {
            cache.put(new Element("wuxc", "shuai xiaohuo"));
        });
        Thread t2 = new Thread(() -> {
            System.out.println(cache.get("wuxc"));
        });
        t1.start();
        TimeUnit.SECONDS.sleep(2);
        t2.start();
    }

    @EnableCaching
    @Configuration
    static class CacheConfig {
        @Bean
        public CacheManager cacheManager() {
            return new EhCacheCacheManager(ehcache());
        }

        @Bean
        public net.sf.ehcache.CacheManager ehcache() {
            return EhCacheManagerUtils.buildCacheManager();
        }

        @Autowired
        CacheManager cacheManager;

        public void putUser() {
            Cache cache = cacheManager.getCache("user");
            cache.put("wuxc", "shuai xiao huo");
        }

        public String getUser() {
            Cache cache = cacheManager.getCache("user");
            return cache.get("wuxc", String.class);
        }
    }

}
