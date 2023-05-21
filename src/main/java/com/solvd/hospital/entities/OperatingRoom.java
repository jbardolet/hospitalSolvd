package com.solvd.hospital.entities;


import com.solvd.hospital.interfaces.IClean;
import com.solvd.hospital.interfaces.IPrint;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.utilities.Utils;

import javax.swing.*;

public class OperatingRoom extends HospitalMaterialResource implements IClean, IPrint {
    private static final Logger logger = LogManager.getLogger("OperatingRoom");
    // private static int counterOR=0;


    public OperatingRoom(int i) {
        super(i);
    }
    public OperatingRoom(){
        super();
    }

    public boolean isAvailable() {
        return super.getAvailable();
    }

    public void setAvailable(boolean available) {
        super.setAvailable(available);
    }

    public int surgery(Nurse nurse, Doctor doctor, Patient patient) {
        nurse.setAvailability(false);
        doctor.setAvailability(false);

        int result = generateResult(patient.getDisease().getEmergency()/2);
        nurse.setAvailability(true);
        doctor.setAvailability(true);
        return result;

    }

    public int generateResult(int emergency){
        int result=Utils.getrandomInt(0,7)-emergency;
        printResults(result);
        return result;
    }
    @Override
    public void clean() {
        // JOptionPane.showMessageDialog(null,"The Operating Room is clean", "OR", JOptionPane.PLAIN_MESSAGE);
        logger.info("The OR is clean");
    }

    @Override
    public void printResults(int result){
        logger.info("The OR result is "+result);
        JOptionPane.showMessageDialog(null,"After surgery, you improve "+ result+" points","OR", JOptionPane.PLAIN_MESSAGE);


    }
}
