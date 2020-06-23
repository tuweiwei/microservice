/**
 * Copyright (C), 2020, XXX公司
 * FileName: Print
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @program: untitled
 * @description:
 * @author: tuwei
 * @create: 2020-06-01 01:24
 */
public class Print implements Runnable{

    /**
     * 多个线程共享这一个sequence数据
     */
    private static int sequence = 1;

    private static final int SEQUENCE_END = 100;

    private Integer id;
    private ReentrantLock lock;
    private Condition[] conditions;
    private String name = "";

    private Print() {
    }

    private Print(String name, Integer id, ReentrantLock lock, Condition[] conditions) {
        this.id = id;
        this.lock = lock;
        this.conditions = conditions;
        this.name = name;
    }

    @Override
    public void run() {
        while (sequence >= 0 && sequence <= SEQUENCE_END) {
            lock.lock();
            try {
                //对序号取模,如果不等于当前线程的id,则先唤醒其他线程,然后当前线程进入等待状态
                while (sequence % 2 != id) {// 0B 1A 2C
                    if (sequence % 10 == 0) {
                        break;
                    }
                    conditions[(id + 1) % conditions.length].signal();
                    conditions[(id + 2) % conditions.length].signal();
                    conditions[id].await();
                }
                //奇数A 偶数B
                System.out.println(name + " : " + sequence);

                sequence = sequence + 1;
                //唤醒当前线程的下一个线程
                conditions[(id + 2) % conditions.length].signal();
                conditions[id].await();
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                //将释放锁的操作放到finally代码块中,保证锁一定会释放
                lock.unlock();
            }
        }

        //数字打印完毕,线程结束前唤醒其余的线程,让其他线程也可以结束
        end();
    }

    private void end() {
        lock.lock();
        conditions[(id + 1) % conditions.length].signal();
        conditions[(id + 2) % conditions.length].signal();
        lock.unlock();
    }


    public void startThread() {
        int threadCount = 3;
        String name = "";
        ReentrantLock lock = new ReentrantLock();
        Condition[] conditions = new Condition[threadCount];
        for (int i = 0; i < threadCount; i++) {
            conditions[i] = lock.newCondition();
        }
        Print[] printNumbers = new Print[threadCount];
        for (int i = 0; i < threadCount; i++) {
            switch (i) {
                case 0:
                    name = "B";
                    break;
                case 1:
                    name = "A";
                    break;
                case 2:
                    name = "C";
                    break;
            }
            Print p = new Print(name, i, lock, conditions);
            printNumbers[i] = p;
        }
        for (Print printNumber : printNumbers) {
            new Thread(printNumber).start();
        }
    }

    public static void main(String[] args) {
        new Print().startThread();
    }
}
