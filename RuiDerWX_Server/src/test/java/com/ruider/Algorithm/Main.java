package com.ruider.Algorithm;

/**
 * 快递员送快递，二维坐标，计算最短路径
 */

import java.util.Scanner;


/**
 * 节点定义
 *
 */
class Point {
    // x坐标点
    int px;
    // y坐标点
    int py;

    public Point(int px, int py) {
        this.px = px;
        this.py = py;

    }

    /**
     * 两个点的距离计算
     * 将所有组合找出来计算最短
     * @param p
     * @return
     */
    public int getLength(Point p) {

        return Math.abs(px - p.px) + Math.abs(py - p.py);

    }

}

/**
 * 主的实现类
 *
 */
class Main {

    static final Point START = new Point(0, 0);

    static int minPath = Integer.MAX_VALUE;

    /**
     * 根据自己的实现想法进行实现，可以新添加子函数等
     *
     * @param start       开始节点
     * @param points      节点序列
     * @return
     */
    public static int calculateMinPath(Point start, Point[] points) {

        return 0;

    }

    /**
     * 主函数
     *
     * @param args
     */
    public static void main(String[] args) {     //面试官，答题结束，希望您能看到

        Scanner input = new Scanner(System.in);
        // 输入行数
        int pnum = Integer.parseInt(input.nextLine().trim());

        Point[] points = new Point[pnum];

        for (int i = 0; i < pnum; i++) {

            String[] locations = input.nextLine().trim().split(",");
            // 输入每个点的坐标
            points[i] = new Point(Integer.parseInt(locations[0]),
                    Integer.parseInt(locations[1]));

        }

        //int min = calculateMinPath(START, points);
        minPath = a(points,0);   //bbbbbbbbb
        System.out.println(minPath);

    }



    public static int a(Point[] points , int n) {
        if(n == points.length) {

            int sum = points[0].getLength(START);
            for (int i = 1 ; i<points.length; i++) {
                sum = sum + points[i-1].getLength(points[i]);

            }
            sum = sum + points[points.length -1].getLength(START);
            minPath = Math.min(minPath , sum);
            return minPath;
        }
        for (int i = n; i < points.length; i++) {
            swap(points,n,i);
            a(points,n+1);
            swap(points,n,i);
        }
        return minPath;
    }

    public static void swap(Point[] points, int i, int j) {
        if (i == j) return;
        Point temp = points[i];
        points[i] = points[j];
        points[j] = temp;
    }

}
