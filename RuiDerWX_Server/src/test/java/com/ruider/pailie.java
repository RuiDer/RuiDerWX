package com.ruider;

public class pailie {
    public static void p(char[] array, int index){
        char temp;
        if(index==array.length){
            System.out.println(array);
            return;
        }
        if(array.length==0||index<0||index>array.length){
            return;
        }
        for(int j=index;j<array.length;j++){
            temp=array[j];
            array[j]=array[index];
            array[index]=temp;
            p(array, index+1);
            temp=array[j];
            array[j]=array[index];
            array[index]=temp;
        }

    }
    public static void main(String[] args) {
        char[] chars={'a','b','c'};

        p(chars,0);

    }
}

