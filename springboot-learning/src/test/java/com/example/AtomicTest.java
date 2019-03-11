package com.example;

import sun.awt.windows.awtLocalization_ko;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.AbstractQueuedSynchronizer.ConditionObject;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @ClassName: Test
 * @Description: TODO
 * @Author Comsys-xianjiao.luo
 * @Date 2019/3/6 17:42
 **/
public class AtomicTest {

    public AtomicInteger atomicInteger = new AtomicInteger(10000);
    public void increase() {
        atomicInteger.getAndIncrement();//i++操作
    }

    //初始容量固定的阻塞队列
    ArrayBlockingQueue arrayBlockingQueue = new ArrayBlockingQueue(1);
    //也是阻塞队列，入队出队都用了加锁，独占锁来保持线程安全，当队空的时候会暂时阻塞，新元素插入到队列的尾部，并且队列获取操作会获得位于队列头部的元素
    //链接队列的吞吐量通常要高于基于数组的队列，但是在大多数并发应用程序中，其可预知的性能要低。
    LinkedBlockingQueue linkedBlockingQueue = new LinkedBlockingQueue();
    //
    ConcurrentLinkedQueue concurrentLinkedQueue = new ConcurrentLinkedQueue();


    public static void test() throws InterruptedException {
        Lock lock = new ReentrantLock();
        //Condition 是一个接口。
        //Condition 接口的实现类是 Lock（AQS）中的 ConditionObject。
        //Lock 接口中有个 newCondition() 方法，通过这个方法可以获得 Condition 对象（其实就是 ConditionObject）。
        //ConditionObject 类是 AQS 的内部类，实现了 Condition 接口
        Condition condition = lock.newCondition();
        lock.lockInterruptibly();

    }
    public static void main(String[] args) {
        final AtomicTest test = new AtomicTest();
        for (int i=0; i<10; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    for (int j=0; j<1000; j++) {
                        test.increase();
                    }
                }
            }).start();
        }
        while (Thread.activeCount() > 1) {
            Thread.yield();//保证main以外的线程都执行完
        }
        System.out.println(test.atomicInteger);
    }
}
