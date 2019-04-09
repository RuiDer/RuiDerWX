package com.ruider;

public class Test {
    int a = 0;
    public Test (int a) {
        this.a = a;
    }
    public static void main(String[] args) {
        int i = 0 ;
        Test a = null;
        Test b = null;
        Test c = null;
        Test d = null;
        Test e = null;
        Test f = null;
        Test g = null;
        Test h = null;
        Test l = null;
        Test j = null;
        Test k = null;
        Test v = new Test(1);
        while(true) {
            a = new Test(i++);
            b = new Test(i++);
            c = new Test(i++);
            d = new Test(i++);
            e = new Test(i++);
            f = new Test(i++);
            g = new Test(i++);
            h = new Test(i++);
            l = new Test(i++);
            j = new Test(i++);
            k = new Test(i++);
            System.out.println(a.a);
        }
    }
}
