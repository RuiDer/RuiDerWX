package com.ruider.ThreadPool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

public class DefaultThreadPool implements ThreadPool {

    private static final int MAX_WORKER_NUMBER = 10;

    private static final int DEFAULT_WORKER_NUMBER = 5;

    private static final int MIN_WORKER_NUMBER = 1;

    private static final LinkedList<Job> jobs = new LinkedList<>();

    private static final List<Worker> workers = Collections.synchronizedList(new ArrayList<Worker>());

    private int workerNum = 0;

    private AtomicLong threadNum = new AtomicLong();


    public DefaultThreadPool () throws Exception{
        workerNum = DEFAULT_WORKER_NUMBER;
        initializeWorker(workerNum);
    }

    public DefaultThreadPool (int num) throws Exception{
        workerNum = num > MAX_WORKER_NUMBER ? MAX_WORKER_NUMBER:num < MIN_WORKER_NUMBER ? MIN_WORKER_NUMBER : num;
        initializeWorker(workerNum);
    }

    @Override
    public void execute (Job job) {
        synchronized (jobs) {
            jobs.addLast(job);
            jobs.notify();           //唤醒一个线程处理该job，notifyAll消耗性能资源
        }
    }

    @Override
    public void addWorker (int num) throws Exception {
        synchronized (jobs) {
            if (workerNum + num > DefaultThreadPool.MAX_WORKER_NUMBER) {
                num = DefaultThreadPool.MAX_WORKER_NUMBER - workerNum;
            }
            initializeWorker(num);
            workerNum += num;
        }
    }

    @Override
    public void removeWorker (int num) {
        synchronized (jobs) {
            if (num > workerNum) {
                throw new IllegalArgumentException("beyond workerNum");
            }
            int count = 0;
            while (count < num) {
                Worker worker = workers.get(count);
                if (workers.remove(worker)) {
                    worker.shutdown();
                    count++;
                }
            }
            this.workerNum -= count;
        }
    }

    @Override
    public void shutdown () {
        for (Worker worker : workers) {
            worker.shutdown();
        }
    }

    @Override
    public int getJobSize () {
        return DefaultThreadPool.jobs.size();
    }

    @Override
    public int gerWorkerSize () {
        return DefaultThreadPool.workers.size();
    }

    public void initializeWorker (int workerNum) throws Exception{
        if (workerNum <= 0) {
            throw new IllegalAccessException("below workerNum");
        }
        for (int i = 0; i < workerNum; i++) {
            Worker worker = new Worker();
            workers.add(worker);
            Thread thread = new Thread(worker, "worker_thread" + threadNum.incrementAndGet());
        }
    }

    static class Worker implements Runnable {

        //是否可以工作，线程工作状态控制
        private volatile static boolean running = true;


        @Override
        public void run () {
            while (running) {
                synchronized (DefaultThreadPool.jobs) {
                    if(DefaultThreadPool.jobs.isEmpty()) {
                        try {
                            jobs.wait();
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                            return;
                        }
                    }
                    else {
                        Job job = jobs.removeFirst();
                        if (job != null) {
                            try {
                                job.run();
                            } catch (Exception e) {
                                e.printStackTrace();
                            }
                        }
                    }
                }
            }
        }

        public void shutdown () {
            this.running = false;
        }
    }
}
