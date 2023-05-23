package com.solvd.hospital.mixHW;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ThreadThread extends Thread{
    private static final Logger logger = LogManager.getLogger("ThreadThread");

    @Override
    public void run(){
        logger.info("Thread extends Thread - run");
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
