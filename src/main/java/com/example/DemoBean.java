package com.example;

import com.hazelcast.core.IMap;
import com.hazelcast.core.IQueue;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * Created by emrah on 19/11/15.
 */
@Component
public class DemoBean implements IDemoBean {

    @Resource(name = "demoMap")
    private IMap<String, String> demoMap;

    @Resource(name = "demoQueue")
    private IQueue<String> demoQueue;

    @Override
    public void addToMap(String key, String value) {
        demoMap.put(key, value);
    }

    @Override
    public void offerToQueue(String item) {
        demoQueue.offer(item);
    }

    @Override
    public String getItem(String key) {
        System.out.println("You should only see this message once. For the key - " + key);
        return demoMap.get(key);
    }
}
