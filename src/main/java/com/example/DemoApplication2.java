package com.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static com.example.ApplicationContextProvider.getApplicationContext;

@SpringBootApplication
@EnableCaching
public class DemoApplication2 {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(DemoApplication2.class, args);
        IDemoBean demoBean = getApplicationContext().getBean(IDemoBean.class);

        for (int i = 10; i < 20; i++) {
            System.out.println("Adding key-value : " + i);
            demoBean.addToMap("key-" + i, "value-" + i);
        }

        for (;;) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("Getting value of key-1");
            demoBean.getItem("key-10");
        }

    }

    @Bean
    public HazelcastInstance getHazelcastInstance() {
        return Hazelcast.newHazelcastInstance();
    }

    @Bean(name = "demoMap")
    public IMap<String, String> getDemoMap() {
        return getHazelcastInstance().getMap("demoMap");
    }

    @Bean(name = "demoQueue")
    public IQueue<String> getDemoQueue() {
        return getHazelcastInstance().getQueue("demoQueue");
    }

}
