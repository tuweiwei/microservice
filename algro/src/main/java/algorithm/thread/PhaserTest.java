/**
 * Copyright (C), 2020, XXX公司
 * FileName: PhaserTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.util.*;
import java.util.concurrent.Phaser;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-24 22:38
 */
public class PhaserTest {
    public static void main(String[] args) {
        //CyclicBarrier只适用于固定数量的参与者,而Phaser适用于可变数目的屏障
        String name = "明刚红丽黑白";
        Phaser phaser = new Phaser(name.length());
        List<Thread> tourismThread = new ArrayList<>();
        for (char ch : name.toCharArray()){
            tourismThread.add(new Thread(new TourismRunnableTest(phaser), "小" + ch));
        }
        for (Thread thread : tourismThread){
            thread.start();
        }







        String visitor = "明刚红丽黑白";
        String kongjie = "美惠花";

        Airplane airplane = new Airplane(visitor.length());
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < visitor.length(); i++){
            threads.add(new Thread(() -> {
                airplane.getOffPlane();
            }, "小" + visitor.charAt(i)));
        }
        for (int i = 0; i < kongjie.length(); i++){
            threads.add(new Thread(() ->{
                airplane.doWork();
            }, "小" + kongjie.charAt(i) + "空姐"));
        }

        for (Thread thread : threads){
            thread.start();
        }
    }
}

// cyclicbarrier
class TourismRunnableTest implements Runnable{
    Phaser phaser;
    Random random;
    public TourismRunnableTest(Phaser phaser) {
        this.phaser = phaser;
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
            //
            phaser.arriveAndAwaitAdvance();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

// 替代 countdownlatch
class Airplane {
    private Phaser phaser;
    private Random random;

    public Airplane(int peopleNum) {
        phaser = new Phaser(peopleNum);
        random = new Random();
    }

    /**
     * 下机
     */
    public void getOffPlane() {
        try {
            String name = Thread.currentThread().getName();
            Thread.sleep(random.nextInt(500));
            System.out.println(name + " 在飞机在休息着....");
            Thread.sleep(random.nextInt(500));
            System.out.println(name + " 下飞机了");
            phaser.arrive();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork() {

        String name = Thread.currentThread().getName();
        System.out.println(name + "准备做 清理 工作");
        phaser.awaitAdvance(phaser.getPhase());
        System.out.println("飞机的乘客都下机," + name + "可以开始做 清理 工作");

    }
}
