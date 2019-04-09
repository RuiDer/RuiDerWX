package com.ruider.ThreadPool;

public interface ThreadPool {

    void execute(Job job);

    void addWorker(int num) throws Exception;

    void removeWorker(int num);

    void shutdown ();

    int getJobSize();

    int gerWorkerSize();
}
