package com.rjp.eaction.sort;

/**
 * author : Gimpo create on 2018/6/5 10:34
 * email  : jimbo922@163.com
 */
public class FastSort {
    public static void main(String[] args) {
        int[] arr = {2,5,3,6,9,7,1,8,4};
        fastSort(arr, 0, arr.length - 1);
        for (int i : arr) {
            System.out.println(i);
        }
    }

    private static void fastSort(int arr[], int low, int high){
        if(low >= high){
            return;
        }
        int index = oneSort(arr, low, high);
        fastSort(arr, low, index - 1);
        fastSort(arr, index + 1, high);
    }

    private static int oneSort(int arr[], int low, int high) {
        int key = arr[low];
        while (low < high) {
            while (low < high && arr[high] >= key) {
                high--;
            }
            arr[low] = arr[high];
            while (low < high && arr[low] <= key) {
                low++;
            }
            arr[high] = arr[low];
        }
        arr[high] = key;
        return high;
    }
}
