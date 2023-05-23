package com.solvd.hospital.mixHW;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadRunnable implements Runnable{
    private static final Logger logger = LogManager.getLogger("ThreadRunnable");
    private int threadNum;
    public ThreadRunnable(int threadNum){
        this.threadNum = threadNum;
    }

    @Override
    public void run() {
        //logger.info("Thread implements Runnable - from thread "+threadNum+" run");
        logger.info("The thread "+threadNum+" using "+Thread.currentThread().getName());
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

    }
}
