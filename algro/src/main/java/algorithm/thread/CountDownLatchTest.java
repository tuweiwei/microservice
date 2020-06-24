/**
 * Copyright (C), 2020, XXX公司
 * FileName: CountDownLatchTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.util.HashSet;
import java.util.Random;
import java.util.Set;
import java.util.concurrent.CountDownLatch;

/**
 * @program: micro-service
 * @description: 实践 多线程的事务回滚
 * @author: tuwei
 * @create: 2020-06-22 22:30
 */
public class CountDownLatchTest {
    //有一个同步助手,可以让一个或一些线程等待直到另外一些线程执行完一些操作.这就是CountDownLatch
    public static void main(String[] args) {
        String visitor = "明刚红丽黑白";
        String kongjie = "美惠花";

        Airplane2 airplane2 = new Airplane2(visitor.length());
        Set<Thread> threads = new HashSet<>();
        for (int i = 0; i < visitor.length(); i ++){
            threads.add(new Thread(() -> {
                airplane2.getOffPlane();
            }, "小" + visitor.charAt(i)));
        }
        for (int i = 0; i < kongjie.length(); i ++){
            threads.add(new Thread(() ->{
                airplane2.doWork();
            }, "小" + kongjie.charAt(i) + "空姐"));
        }

        for (Thread thread : threads){
            thread.start();
        }
    }
}
class Airplane2 {
    private CountDownLatch countDownLatch;
    private Random random;
    public Airplane2(int peopleNum){
        countDownLatch = new CountDownLatch(peopleNum);
        random = new Random();
    }

    /**
     * 下机
     */
    public void getOffPlane(){
        try {
            String name = Thread.currentThread().getName();
            Thread.sleep(random.nextInt(500));
            System.out.println(name + " 在飞机在休息着....");
            Thread.sleep(random.nextInt(500));
            System.out.println(name + " 下飞机了");
            countDownLatch.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void doWork(){
        try {
            String name = Thread.currentThread().getName();
            System.out.println(name + "准备做 清理 工作");
            countDownLatch.await();
            System.out.println("飞机的乘客都下机," + name + "可以开始做 清理 工作");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}