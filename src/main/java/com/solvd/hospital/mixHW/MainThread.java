package com.solvd.hospital.mixHW;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MainThread {
    private static final Logger logger = LogManager.getLogger("MainThread");

    public static void main(String[] args) throws ExecutionException, InterruptedException {


        logger.info("------- Thread extends thread -------");
        ThreadThread threadThread = new ThreadThread();
        threadThread.start();

        logger.info("------- Thread implements runnable -------");
        ThreadRunnable threadRunnable = new ThreadRunnable(1);
        Thread thread = new Thread(threadRunnable);
        thread.start();


        logger.info("------- Thread pool -------");
        ExecutorService executorService = Executors.newFixedThreadPool(5);

        for (int i = 1; i<=7;i++){
            executorService.execute(new ThreadRunnable(i));
        }
        executorService.shutdown();


        logger.info("------- Thread pool with CompletableFuture -------");
        ExecutorService es = Executors.newFixedThreadPool(5);
            for(int i = 1; i<=7; i++){
            int finalI = i;
            CompletableFuture<String> completableFuture = CompletableFuture.supplyAsync(() ->{
                String s = "Thread using "+Thread.currentThread().getName();
                logger.info("The thread cf"+ finalI +" using "+Thread.currentThread().getName());
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                return s;
            },es);
            }
            es.shutdown();







    }





}
