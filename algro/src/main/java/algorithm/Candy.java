/**
 * Copyright (C), 2020, XXX公司
 * FileName: Candy
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm;

/**
 * @program: untitled
 * @description:
 * @author: tuwei
 * @create: 2020-05-30 10:37
 */
public class Candy {

    public static void main(String[] args) {
        int a[]= new int[101];
        int n = 3,i,count=0;
        boolean flag = false;
        a[0]=0;//把a[0]定义为一个类似于缓冲区的东西，用于暂时的存放数据。

        while(!flag) {
            a[0]=a[1]/2;//缓冲区存放第一个小朋友的
            for(i=1;i<n;i++)
                a[i]=a[i]/2+a[i+1]/2;//用循环语句，依次将前n-1个小朋友的糖果传一半给前一个人

            a[n]=a[n]/2+a[0];//由于大家坐成一个圈，所以第n个小朋友把自己的一半去掉之后同时 又得到第一个小朋友糖数的一半
            for(i=1;i<=n;i++)
            {
                if(a[i]%2!=0){
                    a[i]=a[i]+1;
                    count++;
                }
            }       //用一个循环一次检查是否是奇数，并同时统计老师补发糖的数量。

            for(i=1;i<=n;i++)
            {
                if(a[i]==a[1])
                    flag=true;
                else{
                    flag=false;
                    break;
                }
            }  //判断是否每个人糖数是否相等，如果糖数都相等，flag=1，此时while(!flag）跳出循环，
            // 游戏结束。如果糖数不相等，继续游戏。
        }
        System.out.println(count);
    }

}
