package com.ruider.currentTest;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;

public class HashMapConcurrentProblem extends Thread{

    private static Map<Integer, Integer> map = new HashMap<Integer, Integer>();
    private static AtomicInteger at = new AtomicInteger(0);


    @Override
    public void run() {
        while(at.get()<100000000){
            map.put(at.get(), at.get());
            at.incrementAndGet();
        }
    }

    public static void main(String[] args) {
        for(int i=0;i<10;i++){
            Thread thread = new HashMapConcurrentProblem();
            thread.start();
        }
    }

}

