package com.ruider.ThreadPool;

public class Job {
    public void run () {
        try {
            Thread.currentThread().sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
