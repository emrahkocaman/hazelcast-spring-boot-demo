package com.example;

import com.hazelcast.client.HazelcastClient;
import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

import static com.example.ApplicationContextProvider.getApplicationContext;


/**
 * Start at least  one {@link Master} instance before launching client.
 * Add as many instances as you want later to see how queue is processed in a distributed way.
 */
@SpringBootApplication
@EnableCaching
public class ClientDemoApplication {

    public static void main(String[] args) throws InterruptedException {
        SpringApplication.run(ClientDemoApplication.class, args);
        IDemoBean demoBean = getApplicationContext().getBean(IDemoBean.class);

        for (int i = 0; ; i++) {
            System.out.println("Offering item " + i);
            demoBean.offerToQueue("item-" + i);
            TimeUnit.SECONDS.sleep(1);
        }

    }

    @Bean
    public HazelcastInstance getHazelcastInstance() {
        return HazelcastClient.newHazelcastClient();
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
