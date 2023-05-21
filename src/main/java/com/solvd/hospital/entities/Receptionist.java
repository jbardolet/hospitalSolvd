package com.solvd.hospital.entities;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import com.solvd.hospital.utilities.WorkShift;

import javax.swing.*;

public class Receptionist extends Worker{
    private static final Logger logger = LogManager.getLogger("Receptionist");

    public Receptionist(Integer id, String name, String lastName, int age,PhoneNumber phone, Address address,String email, float salary, byte yearsWorHospital, WorkShift workShift) {
        super(id, name, lastName,age, phone, address,email, salary, yearsWorHospital, workShift);
    }

    public Receptionist() {
    }

    @Override
    public void introduceYourself() {
        JOptionPane.showMessageDialog(null,"Hello welcome to the hospital, I am  "+ this+", I am going to help you", "Front desk", JOptionPane.PLAIN_MESSAGE);
        logger.info(super.getName()+" "+super.getLastName()+" has introduced");
    }
    @Override
    public void bePaid() {
        logger.info(super.getName()+" "+super.getLastName()+" has been paid");
        // JOptionPane.showMessageDialog(null,super.getName()+" "+super.getLastName()+" has been paid", "Hospital Services", JOptionPane.PLAIN_MESSAGE);
    }
}

