package chapter03;

/**
 * 项目名称：concurrency
 * 包： chapter03
 * 类名称：NoVisibility.java
 * 类描述：测试共享变量在线程间的内存可见性
 * 创建人：wufuming
 * 创建时间：2018年10月13日
 */
public class NoVisibility {

    private static boolean ready;
    private static int number;

    private static class ReadThread extends Thread{
        @Override
        public void run() {
            while (!ready){
               // Thread.yield();
                System.out.println("test");
            }
            System.out.println(number);
        }
    }
    public static void main(String[] arges) {
        new ReadThread().start();
        number=42;
        ready=true;
    }

}