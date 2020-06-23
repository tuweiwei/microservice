/**
 * Copyright (C), 2020, XXX公司
 * FileName: Bubble
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm;

/**
 * @program: untitled
 * @description:
 * @author: tuwei
 * @create: 2020-05-29 10:54
 */
public class Bubble {

    public static void main(String[] args) {
        int[] arr = {2,5,1,3,8,5,7,4,3};
        bubbleSort(arr);
        for(int a : arr)
            System.out.println(a);
    }

    private static void bubbleSort(int[] arr) {
        int size = arr.length;
        for(int i = 0; i < size; i++){
            for (int j = 0; j < size - i - 1; j++){
                if (arr[j] > arr[j+1]){
                    int temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                }
            }
        }
    }
}