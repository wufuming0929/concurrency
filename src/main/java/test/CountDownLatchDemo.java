package test;

import java.util.concurrent.CountDownLatch;

/**
 * 项目名称：concurrency
 * 包： test
 * 类名称：CountDownLatchTest.java
 * 类描述：闭锁测试
 * 创建人：wufuming
 * 创建时间：2018年11月16日
 */
public class CountDownLatchDemo {

    public static void main(String[] arges) throws InterruptedException {
        //闭锁,不能重复使用
        CountDownLatch latch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread t = new Thread(new CountDownLatchTest(latch));
            t.start();
        }
        //等待计数器为0,否则阻塞
        latch.await();
        System.out.println(Thread.currentThread().getName() + "finish!");

    }

    static class CountDownLatchTest implements Runnable {
        private CountDownLatch latch;

        CountDownLatchTest(CountDownLatch latch) {
            this.latch = latch;
        }

        @Override
        public void run() {
            System.out.println(Thread.currentThread().getName() + "--->countdown!");
            //计数器减1
            latch.countDown();
        }
    }

}