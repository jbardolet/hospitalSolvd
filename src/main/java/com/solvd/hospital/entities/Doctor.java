package com.solvd.hospital.entities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.utilities.Utils;
import com.solvd.hospital.utilities.WorkShift;
import com.solvd.hospital.interfaces.ITreat;

import javax.swing.*;


public class Doctor extends Worker implements ITreat {
    private static final Logger logger = LogManager.getLogger("Doctor");
    private int avility;
    private boolean availability;

    public Doctor(Integer id, String name, String lastName,int age, PhoneNumber phone, Address address, String email, float salary, byte yearsWorHospital, WorkShift workShift) {
        super(id, name, lastName,age, phone, address,email, salary, yearsWorHospital, workShift);
        this.avility= Utils.getrandomInt(2,10);
        this.availability = true;
    }

    public Doctor() {
        super();
    }

    public int getAvility() {
        return avility;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public void treatment(Patient p){
        int efect = (int)(avility*p.getDisease().getImprovementRate())/100;

        p.setCount(p.getCount()-efect);
    }
    @Override
    public void introduceYourself() {

        JOptionPane.showMessageDialog(null,"Hello I am Dr. "+super.getName()+" "+ super.getLastName(),"",JOptionPane.PLAIN_MESSAGE);
        logger.info("Dr. "+super.getName()+" "+super.getLastName()+" has introduced");
    }
    @Override
    public void bePaid() {

        // JOptionPane.showMessageDialog(null,super.getName()+" "+super.getLastName()+" has been paid", "Hospital Services", JOptionPane.PLAIN_MESSAGE);
        logger.info("Dr. "+super.getName()+" "+super.getLastName()+" has been paid");

    }
}

