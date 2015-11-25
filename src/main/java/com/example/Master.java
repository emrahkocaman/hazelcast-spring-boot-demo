package com.example;

import com.hazelcast.core.Hazelcast;
import com.hazelcast.core.HazelcastInstance;

import java.util.concurrent.BlockingQueue;

/**
 * Use this class to start instances. They are supposed to be connected by ClientDemoApplication
 * Items produced by ClientDemoApplication will be processed by the instance started by this class.
 */
public class Master {
    public static void main(String[] args) throws InterruptedException {
        HazelcastInstance instance = Hazelcast.newHazelcastInstance();
        BlockingQueue<Object> demoQueue = instance.getQueue("demoQueue");
        for(;;) {
            System.out.println("Processed : " + demoQueue.take());
        }
    }
}
