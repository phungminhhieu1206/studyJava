package com.hieudev.multithreading;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Vòng đời của 1 thread:
 *  new -> runable (start nhưng chưa được gọi), -> running -> non-runable -> terminated (khi thoát run())
 * Có 2 cách tạo thread trong java:
 *  + extends lớp Thread
 *  + implement Runable interface
 * Một số phương thức hay dùng:
 *  + run(): thực hiện hành động trong thread
 *  + sleep(long milisecond): tạm dừng thread trong x mili second
 *  + interrupt(): ngắt thread
 *  -> Tại một thời điểm chỉ có 1 thread được thực thi, nếu dùng sleep() thì thread này sẽ dừng,
 *  khi đó thread scheduler sẽ tìm thread khác ở trạng thái runable để thực thi.
 *  -> Một thread không thể cố tình gọi start() 2 lần, exception: IllegalThreadStateException
 * Chạy nhiều thread trong java: -> thread pool
 *
 */
public class MultiThreading extends Thread {
    // overriding
    public void run() {
        System.out.println("thread is running");
    }

    public static void main(String[] args) {
        MultiThreading threading = new MultiThreading();
        threading.start();

        // Thạo threadpool 4 thread
        ExecutorService executorService = Executors.newFixedThreadPool(4);

        // thực hiện 10 công việc
        for (int i = 0; i < 10; i++) {
            executorService.submit(() -> {
                System.out.println(Thread.currentThread().getName() + " is running");
            });
        }

        // Dừng threadpool sau khi done 10 task
        executorService.shutdown();
    }
}

