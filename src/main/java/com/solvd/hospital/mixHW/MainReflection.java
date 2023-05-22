package com.solvd.hospital.mixHW;

import com.solvd.hospital.entities.Address;
import com.solvd.hospital.entities.Nurse;
import com.solvd.hospital.entities.PhoneNumber;
import com.solvd.hospital.utilities.WorkShift;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    private static final Logger logger = LogManager.getLogger("MainReflection");
    public static void main(String[] args) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {

        Class classNurse = Class.forName("com.solvd.hospital.entities.Nurse");
        Field[] nurseFields = classNurse.getDeclaredFields();
        Method[] nurseMethods = classNurse.getDeclaredMethods();

        logger.info("--------Nurse reflection - fields -------- ");
        Nurse nurse = new Nurse(3003,"aaaa","bbbb",40,new PhoneNumber(),new Address(),"asdas@oso.sos", 30f, (byte) 3, WorkShift.AFTERNON);
        //Field[] nurseFields = nurse.getClass().getDeclaredFields();
        for(Field field: nurseFields){
            logger.info(field.getName()+" - "+ field.getType());

            if(field.getName().equals("availability")){
                logger.info("Before reflection "+nurse.isAvailability());
                field.setAccessible(true);
                field.set(nurse,false);
                logger.info("After reflection "+nurse.isAvailability());
            }
        }

        logger.info("--------Nurse reflection - methods --------");
        //Method[] nurseMethods = nurse.getClass().getDeclaredMethods();
        for(Method method: nurseMethods){
            logger.info(method.getName()+" - "+method.getReturnType());
            if(method.getName().equals("introduceYourself")){
                method.invoke(nurse);
            }
        }


    }
}
