package com.solvd.hospital.entities;


import com.solvd.hospital.interfaces.IClean;
import com.solvd.hospital.interfaces.IPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.utilities.Utils;

import javax.swing.*;
import java.util.function.Consumer;

public class Lab extends HospitalMaterialResource implements IClean, IPrint {
    private static final Logger logger = LogManager.getLogger("Lab");

    public Lab(int id) {
        super(id);

    }
    public Lab(){
        super();
    }

    public boolean isAvailable() {
        return super.getAvailable();
    }

    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }

    public int generateResults(){
        int results = Utils.getrandomInt(1,2);
        printResults(results);
        return results;
    }

    @Override
    public void clean() {
        // JOptionPane.showMessageDialog(null,"The Lab is clean","LAB - Services",1);
        logger.info("The lab is clean");

    }

    @Override
    public void printResults(int results){

        if(results==1){
            JOptionPane.showMessageDialog(null,"You are better than we expected, you don't need surgery.","LAB - Services",1);
            logger.info("Lab result indicates patient does not need surgery");
        }else {
            JOptionPane.showMessageDialog(null,"You need surgery.","LAB - Services",1);
            logger.info("Lab result indicates that the patient need surgery");
        }


    }

    public void printLambda(Consumer<Integer> filter){
        if(this.generateResults()==1){
            filter.accept(1);
        }else{
            filter.accept(2);
        }

    }
}

