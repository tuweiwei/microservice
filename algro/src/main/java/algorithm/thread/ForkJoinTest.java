/**
 * Copyright (C), 2020, XXX公司
 * FileName: ForkJoinTest
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
package algorithm.thread;

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.concurrent.*;

/**
 * @program: micro-service
 * @description:
 * @author: tuwei
 * @create: 2020-06-21 20:44
 */
public class ForkJoinTest {

    public static void main(String[] args) {
//        long[] numbers = LongStream.rangeClosed(1, 10000000).toArray();
//        Instant start = Instant.now();
//        calc(numbers);
//        Instant end = Instant.now();
//        System.out.println("耗时：" + Duration.between(start, end).toMillis() + "ms");
//
//        System.out.println("结果为：" ); // 打印结果500500


        futureTest();
    }

    private void timeTest(){

        Timer timer = new Timer();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("炸弹爆炸时间:" + dateFormat.format(new Date()));
            }
        }, 4000);
        System.out.println("炸弹安装时间:" + dateFormat.format(new Date()));

    }


    private static long calc(long[] longs){
        int parallism = Runtime.getRuntime().availableProcessors();
        ExecutorService pool = Executors.newFixedThreadPool(parallism);
        // 也可以使用公用的线程池 ForkJoinPool.commonPool()：
        // pool = ForkJoinPool.commonPool()
        ForkJoinPool  poolForkJoin = new ForkJoinPool();

        class SumTaskForkJoin extends RecursiveTask<Long> {
            private long[] numbers;
            private int from;
            private int to;

            public SumTaskForkJoin(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            //此方法为ForkJoin的核心方法：对任务进行拆分  拆分的好坏决定了效率的高低
            @Override
            protected Long compute() {

                // 当需要计算的数字个数小于6时，直接采用for loop方式计算结果
                if (to - from < 6) {
                    long total = 0;
                    for (int i = from; i <= to; i++) {
                        total += numbers[i];
                    }
                    return total;
                } else { // 否则，把任务一分为二，递归拆分(注意此处有递归)到底拆分成多少分 需要根据具体情况而定
                    int middle = (from + to) / 2;
                    SumTaskForkJoin taskLeft = new SumTaskForkJoin(numbers, from, middle);
                    SumTaskForkJoin taskRight = new SumTaskForkJoin(numbers, middle + 1, to);
                    taskLeft.fork();
                    taskRight.fork();
                    return taskLeft.join() + taskRight.join();
                }
            }
        }
        Long result = poolForkJoin.invoke(new SumTaskForkJoin(longs, 0, longs.length - 1));
        pool.shutdown();
        //return result;

        class SumTask implements Callable<Long>{
            private long[] numbers;
            private int from;
            private int to;

            public SumTask(long[] numbers, int from, int to) {
                this.numbers = numbers;
                this.from = from;
                this.to = to;
            }

            @Override
            public Long call() throws Exception {
                long total = 0;
                for (int i = from; i <= to; i++) {
                    total += numbers[i];
                }
                return total;
            }
        }

        List<Future<Long>> results = new ArrayList<>();
        int part = longs.length / parallism;
        for (int i = 0; i < parallism; i++) {
            int from = i * part; //开始位置
            int to = (i == parallism - 1) ?
                    longs.length - 1 : (i + 1) * part - 1; //结束位置

            //扔给线程池计算
            results.add(pool.submit(new SumTask(longs, from, to)));
        }
        //把每个线程的结果相加，得到最终结果 get()方法 是阻塞的
        // 优化方案：可以采用CompletableFuture来优化  JDK1.8的新特性
        long total = 0L;
        for (Future<Long> f : results) {
            try {
                total += f.get();
            } catch (Exception ignore) {
            }
        }
        return total;
    }




    public static void futureTest() {
        CompletableFuture<String> future1 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future1 finished!");
            return "future1 finished!";
        });

        CompletableFuture<List<String>> future2 = CompletableFuture.supplyAsync(() -> {
            try {
                Thread.sleep(100000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("future2 finished!");
            return new ArrayList<String>(){{
                add("aaaa");
                add("bbbb");
            }};
        });

        CompletableFuture<Void> combindFuture = CompletableFuture.allOf(future1, future2);

        try {
            System.out.println("fw");
            combindFuture.get();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        System.out.println("future1: " + future1.isDone() + " future2: " + future2.isDone());
    }

}
