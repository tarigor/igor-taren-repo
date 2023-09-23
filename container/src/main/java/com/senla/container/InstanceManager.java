package com.senla.container;

import java.util.HashMap;

public class InstanceManager {
    private static HashMap<String, Object> instances = new HashMap<>();

    public static void createAndPutInstance(Object instance, String key) {
        instances.put(key, instance);
    }

    public static void setInstances(HashMap<String, Object> instances) {
        InstanceManager.instances.putAll(instances);
    }

    public static Object getInstance(String key) {
        return instances.get(key);
    }

    public static HashMap<String, Object> getMap(){
        return instances;
    }
}
