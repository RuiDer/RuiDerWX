package com.ruider;

public class MyThread extends Thread {

    private Thread t;
    public MyThread(String name) {
        t = new Thread();
        t.setName(name);
    }

    @Override
    public void run() {
        print();
    }

    public void print() {
        System.out.println(t.getName());
    }

    public static void main(String[] args) {
        MyThread myThread = new MyThread("hello");
        myThread.start();
    }
}
