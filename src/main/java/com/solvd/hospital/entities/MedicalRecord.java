package com.solvd.hospital.entities;


import com.solvd.hospital.interfaces.IUpgrade;
import com.solvd.hospital.utilities.MedicalSpecialties;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.*;
import java.util.Calendar;

public class MedicalRecord implements IUpgrade {
    private static final Logger logger = LogManager.getLogger("OperatingRoom");
    private String path;
    private String idPatient;
    private MedicalSpecialties medicalSpecialty;

    public MedicalRecord(int idPatient){

        this.idPatient = String.valueOf(idPatient);


        Calendar cal = Calendar.getInstance();
        String day = String.valueOf(cal.get(Calendar.DATE));
        String month = String.valueOf(cal.get(Calendar.MONTH));
        String year = String.valueOf(cal.get(Calendar.YEAR));
        //generate a new file in the folder where it is executed

        this.path = System.getProperty("user.dir") + File.separator + "MedicalRecords"+File.separator + idPatient+"_"+day+"_"+month+"_"+year+".txt";

        File file = new File(path);
        try{
            file.createNewFile();
            logger.info("File successfully created");

        }catch(IOException e){
            logger.info("File not created");

        }

    }

    public MedicalRecord() {
    }

    @Override
    public void upgrade(String s) {

    }

    public void addSpeciality(MedicalSpecialties medicalSpecialty){
        File file = new File(path);
        try {
            FileUtils.writeStringToFile(file,medicalSpecialty.toString(),"UTF-8", true);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public void printResults(int i) {

    }

    public MedicalSpecialties getMedicalSpecialty() {
        return medicalSpecialty;
    }

    public void setMedicalSpecialty(MedicalSpecialties medicalSpecialty) {
        this.medicalSpecialty = medicalSpecialty;
    }
}

