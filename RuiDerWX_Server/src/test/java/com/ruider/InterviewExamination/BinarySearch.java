package com.ruider.InterviewExamination;

/**
 * 有序数组查找
 */
public class BinarySearch {

    public static int get(int target, int[] array) {
        if (array == null || array.length == 0) { return -1; }
        int high = array.length -1;
        int low = 0;
        int middle = 0;
        if (target < array[low] || target > array[high]) {return -1;}

        while (low<high) {
            middle = (low + high) / 2 ;
            if (target >= array[low] && target < array[middle]) {
                if (low == middle || middle == high) {
                    break;
                }
                high = middle;
            }
            if (target >= array[middle] && target <= array[high]) {
                if (low == middle || middle == high) {
                    break;
                }
                low = middle;
            }
        }
        if(array[low] == target) { return low; }
        if(array[high] == target) { return high; }
        return -1;

    }


    public static void main(String[] args) {
        int[] array = new int[]{1,3,4,6,8,10};
        System.out.println(BinarySearch.get(1,array));
    }

}
