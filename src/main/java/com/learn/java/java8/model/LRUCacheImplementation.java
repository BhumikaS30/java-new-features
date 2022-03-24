package com.learn.java.java8.model;

import java.util.Deque;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

class LRUCache {

    private final Deque<Integer> queue;
    private final Map<Integer, Integer> hashMap;
    private final int capacity;

    public LRUCache(int capacity) {
        this.capacity = capacity;
        hashMap = new HashMap<>();
        queue = new LinkedList<>();
    }

    public int get(int key) {
        if (!hashMap.containsKey(key)) {
            if (queue.size() != capacity) {
                return hashMap.get(key);
            }
        }
        return -1;
    }

    public void put(int key, int value) {
        //cache=Cache(2)
        //cache.put(1,1)
        //cache.put(2,2)
        //here we have: capacity == cache.size()
        //cache.put(3,3) - evict least recently used item from cache before applying put
        if (queue.size() == capacity) {
            //evict
            int last = queue.getLast();
            hashMap.remove(last);
            queue.remove(last);
            queue.push(key);
        }
        else {
            queue.remove(key);   // 1,2
        }
        hashMap.put(key, value);
        queue.push(key);
    }
}


public class LRUCacheImplementation {
    public static void main(String args[]) throws Exception {
        LRUCache lruCache = new LRUCache(2);
        lruCache.put(1, 1);
        lruCache.put(2, 2);
        System.out.println(lruCache.get(1));
        lruCache.put(3, 3);
        lruCache.put(4, 4);
        System.out.println(lruCache.get(2));
    }
}
