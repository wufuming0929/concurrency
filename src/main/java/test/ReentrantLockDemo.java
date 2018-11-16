package test;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 项目名称：concurrency
 * 包： test
 * 类名称：ReentrantLockDemo.java
 * 类描述：重入锁debug测试
 * 创建人：wufuming
 * 创建时间：2018年10月16日
 */
public class ReentrantLockDemo {

    private int a;

    private ReentrantLock lock = new ReentrantLock();

    public void write() {
        lock.lock();
        try {
            init();
            a++;

        } finally {
            lock.unlock();
        }
    }

    private void init() {
        lock.lock();
        try {
            a = 1;
        } finally {
            lock.unlock();
        }
    }

    public void read() {
        lock.lock();
        try {
            int i = a;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] arges) {
        ReentrantLockDemo lockTest = new ReentrantLockDemo();
        new Thread(() -> lockTest.write()).start();
        new Thread(() -> lockTest.read()).start();
        new Thread(() -> lockTest.read()).start();
        //  new Thread(() -> lockTest.read()).start();
    }

}