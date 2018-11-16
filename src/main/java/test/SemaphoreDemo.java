package test;

import java.util.concurrent.Semaphore;

/**
 * 项目名称：concurrency
 * 包： test
 * 类名称：SemaphoreDemo.java
 * 类描述：信号量测试
 * 创建人：wufuming
 * 创建时间：2018年11月16日
 */
public class SemaphoreDemo {
    public static void main(String[] arges) {
        //信号量,可以限制同时访问系统的线程数,做限流使用
        Semaphore semaphore = new Semaphore(5);
        for (int i = 0; i < 10; i++) {
            Thread t = new Thread(new SemaphoreTest(semaphore));
            t.start();
        }

    }

    static class SemaphoreTest implements Runnable {
        Semaphore semaphore;

        SemaphoreTest(Semaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            try {
                //获取许可并减1,拿不到就阻塞直到有许可
                semaphore.acquire();
                System.out.println(Thread.currentThread().getName() + "-->get permits!");
                Thread.sleep(1000);
                //执行完释放许可,许可加1并notify阻塞线程
                semaphore.release();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }


}