package test;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * 项目名称：concurrency
 * 包： test
 * 类名称：CyclicBarrieDemo.java
 * 类描述：栅栏测试
 * 创建人：wufuming
 * 创建时间：2018年11月16日
 */
public class CyclicBarrieDemo {
    public static void main(String[] arges) throws InterruptedException {

        //栅栏,可以重复使用,参数中的线程会被把计数器扣减至0的线程执行
        CyclicBarrier cyclicBarrier = new CyclicBarrier(5, () -> {
            System.out.println(Thread.currentThread().getName() + " more work!");
        });
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicBarrieTest(cyclicBarrier));
            t.start();
        }
        Thread.sleep(100);
        //再次使用
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CyclicBarrieTest(cyclicBarrier));
            t.start();
        }

    }

    static class CyclicBarrieTest implements Runnable {
        CyclicBarrier barrier;

        CyclicBarrieTest(CyclicBarrier barrier) {
            this.barrier = barrier;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + " barrie!");
            try {
                //计数器减1,计数器不为0,阻塞
                //当为0时,会notifyAll阻塞线程并重复恢复初始值
                barrier.await();
            } catch (InterruptedException | BrokenBarrierException e) {
                e.printStackTrace();
            }
        }
    }

}