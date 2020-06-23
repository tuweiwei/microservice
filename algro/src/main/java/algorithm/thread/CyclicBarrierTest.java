/**
 * Copyright (C), 2020, XXX公司
 * FileName: CyclicBarrierTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-22 22:10
 */
public class CyclicBarrierTest {
    public static void main(String[] args) {
        String name = "明刚红丽黑白";
        // 当所有线程到达屏障 name.length()  即可执行下面的run方法
        CyclicBarrier cyclicBarrier = new CyclicBarrier(name.length(), new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName() +
                        " 清点人数,1,2,3...,ok,人到齐了,准备出发..... go go go....");
            }
        });
        List<Thread> tourismThread = new ArrayList<>();
        for (char ch : name.toCharArray()){
            tourismThread.add(new Thread(new TourismRunnable(cyclicBarrier), "小" + ch));
        }
        for (Thread thread : tourismThread){
            thread.start();
        }
    }
}

class TourismRunnable implements Runnable{
    CyclicBarrier cyclicBarrier;
    Random random;
    public TourismRunnable(CyclicBarrier cyclicBarrier) {
        this.cyclicBarrier = cyclicBarrier;
        this.random = new Random();
    }

    @Override
    public void run() {
        tourism();
    }

    /**
     * 旅游过程
     */
    private void tourism() {
        goToStartingPoint();
        goToHotel();
        goToTourismPoint1();
        goToTourismPoint2();
        goToTourismPoint3();
        goToEndPoint();
    }

    /**
     * 装备返程
     */
    private void goToEndPoint() {
        goToPoint("飞机场,准备登机回家");
    }

    /**
     * 到达旅游点3
     */
    private void goToTourismPoint3() {
        goToPoint("旅游点3");
    }

    /**
     * 到达旅游点2
     */
    private void goToTourismPoint2() {
        goToPoint("旅游点2");
    }

    /**
     * 到达旅游点1
     */
    private void goToTourismPoint1() {
        goToPoint("旅游点1");
    }

    /**
     * 入住酒店
     */
    private void goToHotel() {
        goToPoint("酒店");
    }

    /**
     * 出发点集合
     */
    private void goToStartingPoint() {
        goToPoint("出发点");
    }

    private int getRandomTime(){
        int time = this.random.nextInt(400) + 100;
        try {
            Thread.sleep(time);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return time;
    }

    private void goToPoint(String point){
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + " 花了 " + getRandomTime() + " 时间才到了" + point);
            cyclicBarrier.await();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


