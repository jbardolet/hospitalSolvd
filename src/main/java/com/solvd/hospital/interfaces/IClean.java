package com.solvd.hospital.interfaces;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public interface IClean {
    static final Logger logger = LogManager.getLogger("ICleanable");


    void clean();
    default void defaultCleaning(){
        logger.info("Night turn cleaning");
    }
}
