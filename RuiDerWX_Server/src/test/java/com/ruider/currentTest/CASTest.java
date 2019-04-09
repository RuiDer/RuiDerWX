package com.ruider.currentTest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

public class CASTest {
    int i = 0;
    AtomicInteger atomicI = new AtomicInteger(0);
    public void count() {i++; System.out.println("i-"+i);}
    public void casCount() {
        for(;;) {
            int i = atomicI.get();
            boolean ifChange = atomicI.compareAndSet(i, ++i);
            if(ifChange) {
                System.out.println("atomicI-"+atomicI);
                break;
            }
        }
    }

    public static void main(String[] args) {
        CASTest cas = new CASTest();
        List<Thread> list = new ArrayList<>(600);
        Long start = System.currentTimeMillis();
        for (int i = 0; i<100; i++) {
            Thread t = new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j =0; j<100; j++) {
                        cas.count();
                        cas.casCount();
                    }
                }
            });
            list.add(t);
        }
        for(Thread thread : list) {
            thread.start();
        }
        for(Thread thread : list) {
            try{thread.join();}
            catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("count:"+ cas.i);
        System.out.println("count:"+ cas.atomicI.get());
        System.out.println("time:"+ (System.currentTimeMillis()-start)+ "秒");
    }
}
