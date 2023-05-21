package com.solvd.hospital.application;

import com.solvd.hospital.entities.*;

import com.solvd.hospital.exceptions.LinkedListException;
import com.solvd.hospital.utilities.*;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.swing.*;
import java.util.*;


public class EmmergencyApp {

    private Hospital hospital;
    private WorkShift workShift;
    private Queue<Patient> newPatientLine;
    private HandleResourcesUtils handleResourcesUtils;
  //  private final static int NUM_TREATMENTS = 6;
    private static final Logger logger = LogManager.getLogger("EmmergencyApp");

    public EmmergencyApp(Hospital hospital, WorkShift workShift){
        this.hospital = hospital;
        this.workShift = workShift;
        this.newPatientLine = new PriorityQueue<Patient>();
        this.handleResourcesUtils = new HandleResourcesUtils(hospital, workShift);
    }

    public void patientsFirstLine() {
        do{
            checkIn();
        }while(JOptionPane.showConfirmDialog(null,"Is there anyone else in line",hospital.getName(),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION);
        while(!newPatientLine.isEmpty()){
            startPath();
        }
        otherHospitalOptions();

    }
    private void checkIn(){
        JOptionPane.showMessageDialog(null,"Welcome to the hospital "+hospital.getName(),"",1);
        logger.info("The patient enter to the hospital");
        String id = JOptionPane.showInputDialog(null, "Please, introduce your ID number", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
        while(!DataValidatorUtils.isNumeric(id)){
            id = JOptionPane.showInputDialog(null,"Your ID is incorrect, please put a valid one", hospital.getName(),JOptionPane.ERROR_MESSAGE);
        }

        int identification = Integer.parseInt(id);

        Patient patient;

        if(!isOldPatient(identification)){

            //we need all the information to create a patient
            String name= JOptionPane.showInputDialog(null, "Please, introduce your Name", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while(!DataValidatorUtils.isAlpha(name)){
                name=JOptionPane.showInputDialog(null,"Your name is incorrect, please put a valid one", hospital.getName(),JOptionPane.ERROR_MESSAGE);
            }
            String lastName= JOptionPane.showInputDialog(null, "Please, introduce your Last Name", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while(!DataValidatorUtils.isAlpha(lastName)){
                lastName=JOptionPane.showInputDialog(null,"Your last name is incorrect, please introduce a valid one", hospital.getName(),JOptionPane.ERROR_MESSAGE);
            }
            String age=JOptionPane.showInputDialog(null, "Please, introduce your age", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
            while(!DataValidatorUtils.isValidAge(age)){
                age = JOptionPane.showInputDialog(null, "Your age is invalid, please introduce a valid one", hospital.getName(),JOptionPane.PLAIN_MESSAGE);

            }
            String phoneNumber= JOptionPane.showInputDialog(null, "Please, introduce your Phone number", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while(!DataValidatorUtils.isPhoneValid(phoneNumber)){
                phoneNumber=JOptionPane.showInputDialog(null,"Your phone number is incorrect, please introduce a valid one", hospital.getName(),JOptionPane.ERROR_MESSAGE);

            }
            String direction= JOptionPane.showInputDialog(null, "Please, introduce your direction", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            String city= JOptionPane.showInputDialog(null, "Please, introduce your city", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while (!DataValidatorUtils.isAlpha(city)){
                city =JOptionPane.showInputDialog(null,"Your City is incorrect, please introduce a valid city", hospital.getName(),JOptionPane.ERROR_MESSAGE);
            }
            String country= JOptionPane.showInputDialog(null, "Please, introduce your Country", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while (!DataValidatorUtils.isAlpha(country)){
                country =JOptionPane.showInputDialog(null,"Country is incorrect, please introduce a valid country", hospital.getName(),JOptionPane.ERROR_MESSAGE);
            }
            String zip= JOptionPane.showInputDialog(null, "Please, introduce your ZIP code", hospital.getName(),JOptionPane.PLAIN_MESSAGE);
            while (!DataValidatorUtils.isZIPValid(zip)){
                zip =JOptionPane.showInputDialog(null,"Your ZIP code is incorrect, please introduce a valid one", hospital.getName(),JOptionPane.ERROR_MESSAGE);
            }

            //create address
            Address address = new Address(direction,city,country,zip);
            logger.info("New address created "+ address);
            String email = JOptionPane.showInputDialog(null, "Please, introduce your email", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
            while (!DataValidatorUtils.isEmailValid(email)){
                email=JOptionPane.showInputDialog(null,"Email incorrect, please introduce a valid one", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
            }
            ArrayList<MedicalRecord> m = new ArrayList<>();
            m.add(new MedicalRecord(identification));
            patient = new Patient(identification,name,lastName,Integer.parseInt(age),new PhoneNumber("",phoneNumber),address,email, m);
            hospital.addPatient(patient);
            logger.info("New patient created and added to the hospital records"+patient);
        }else{
            patient = getExistingPatient(identification);
            logger.info("Existing patient "+patient);
            JOptionPane.showMessageDialog(null,"Welcome back to"+hospital.getName(),"",1);
            MedicalRecord mNew = new MedicalRecord(patient.getId());
            patient.setDisease(new Disease());
            patient.recalculateCount();
            logger.info("Existing patient - new disease"+patient);

        }

        //add patients to the priority line - patient implements comparable with count
        newPatientLine.add(patient);




    }
    public void startPath(){

        Patient patient = newPatientLine.poll();
        JOptionPane.showMessageDialog(null,"Hello patient "+patient.getName()+ " "+patient.getLastName()+" it's your turn","Hospital Queue",1);
        //level between 0 and 20
        logger.info("New patient "+patient.getName()+" "+ patient.getLastName()+" starts with level "+patient.getCount());



        Receptionist receptionist = handleResourcesUtils.firstReceptionist();
        receptionist.setAvailability(false);
        try{
            hospital.getReceptionists().workerWorked(receptionist);
        }catch (LinkedListException ex){
            logger.error(ex.getMessage());
        }
        receptionist.introduceYourself();

        //assign resources to the patient
        Lab lab = handleResourcesUtils.firstLab();
        Nurse nurse = handleResourcesUtils.firstNurse();
        Doctor doctor = handleResourcesUtils.firstDoctor();
        Bed bed= handleResourcesUtils.firstBed();


        receptionist.setAvailability(true);
        startTreatment(bed, doctor, nurse, patient,lab);

    }

    private void startTreatment(Bed bed, Doctor doctor, Nurse nurse, Patient patient, Lab lab) {
        lab.setAvailable(false);
        bed.setAvailability(false);
        doctor.setAvailability(false);
        nurse.setAvailability(false);
        try {
            hospital.getDoctors().workerWorked(doctor);
            hospital.getNurses().workerWorked(nurse);
        }catch (LinkedListException e){
            logger.error(e.getMessage());
        }


        int labresult = lab.generateResults();
        lab.clean();
        lab.setAvailable(true);

        if(labresult==2){
            Doctor docOR = handleResourcesUtils.firstDoctor();
            docOR.setAvailability(false);
            Nurse nurseOR = handleResourcesUtils.firstNurse();
            nurseOR.setAvailability(false);
            try {
                hospital.getDoctors().workerWorked(docOR);
                hospital.getNurses().workerWorked(nurseOR);
            }catch (LinkedListException e){
                logger.error(e.getMessage());
            }
            OperatingRoom or = handleResourcesUtils.firstOR();
            or.setAvailable(false);
            int orResult = or.surgery(nurseOR,docOR, patient);
            patient.setCount(patient.getCount()-orResult);
            docOR.setAvailability(true);
            nurseOR.setAvailability(true);
            or.clean();
            or.setAvailable(true);
            logger.info("The patient currently has a level of "+patient.getCount()+" we can continue with the treatment.");
        }


        doctor.introduceYourself();
        nurse.introduceYourself();


        int count =1;
        while(patient.getPatientState()== PatientState.HOSPITALISED&& count< HospitalNumbers.NUM_TREATMENTS.getValue()){
            logger.info("Treatment "+count);
            JOptionPane.showMessageDialog(null,"Treatment "+count,"Emergency Hospital",JOptionPane.PLAIN_MESSAGE);
            treatment(patient, nurse, doctor);
            showState(patient);

            count++;
        }
        bed.clean();
        bed.setAvailability(true);
        doctor.setAvailability(true);
        nurse.setAvailability(true);

        if(patient.getPatientState()== PatientState.HOSPITALISED){
            JOptionPane.showMessageDialog(null, "You will be moved to another hospital wing");
            logger.info("After 5 treatments, the patient will be move to another wing");
        }

    }

    private int treatment(Patient patient, Nurse nurse, Doctor doctor){
        nurse.treatment(patient);
        doctor.treatment(patient);
        JOptionPane.showMessageDialog(null, "Your level is: "+patient.getCount());
        logger.info("The patient level is: "+patient.getCount());
        return patient.getCount();
    }

    private void showState(Patient patient){
        if(patient.getCount()<PatientState.TREATED.getValue()){
            patient.setPatientState(PatientState.TREATED);
            JOptionPane.showMessageDialog(null, "You are treated");
            logger.info("The patient is treated");
        }else if(patient.getCount()<PatientState.HOSPITALISED.getValue()){
            JOptionPane.showMessageDialog(null, "You still need treatment");
            logger.info("The patient still need treatment");
            patient.setCount(patient.getCount()+(patient.getDisease().getAggravation()));
        }else{
            if(patient.getCount()-patient.getLuck() >=PatientState.DEAD.getValue()){
                patient.setPatientState(PatientState.DEAD);
                JOptionPane.showMessageDialog(null, "Not enough luck, you are dead with level: "+patient.getCount());
                logger.info("Not enough luck, the patient is dead with level: "+patient.getCount()+" "+PatientState.DEAD.getDescription());
            }else{
                JOptionPane.showMessageDialog(null, "You still need treatment");
                logger.info("You still need treatment");
                patient.setCount(patient.getCount()-patient.getLuck()+(patient.getDisease().getAggravation()));
            }

        }
    }




    private boolean isOldPatient(int id){
        Set<Patient> oldPtients = hospital.getOldPatients();

        return oldPtients.stream()
                .anyMatch(Patient -> Patient.getId().equals(id));

    }

    private Patient getExistingPatient(int id){

        Set<Patient> oldPtients = hospital.getOldPatients();

        return oldPtients.stream()
                            .filter(Patient ->Patient.getId().equals(id))
                            .findAny().orElse(null);

    }

    public void otherHospitalOptions(){
        // final menu
        String mainOption =JOptionPane.showInputDialog(null, "Please, introduce your option\n"+
                "1. Pay the salary to the hospital workers"+
                "\n 2. Show all the workers by alph-order (last Name)"+
                "\n 3. Show workers over 60 years old"+
                "\n 4. Do you...."+
                "\n 5. Exit", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
        while(!DataValidatorUtils.isValidMenuOption(mainOption)){
            mainOption = JOptionPane.showInputDialog(null, "Your option is invalid, please introduce a valid one", hospital.getName(),JOptionPane.PLAIN_MESSAGE);

        }
        int mo = Integer.parseInt(mainOption);

        switch (mo){
            case 1:
                hospital.getWorkers().forEach(Worker::bePaid);
                otherHospitalOptions();
                break;
            case 2:
                //Printable - my own interface
                hospital.printAllWorkers(()->{
                    logger.info("Workers: "+hospital.getWorkers().stream()
                            .sorted(Comparator.comparing(worker ->worker.getLastName()))
                            .toList());
                });
                otherHospitalOptions();
                break;
            case 3:
                //predicate
                logger.info(hospital.printPersonFiter((Worker worker) -> (worker.getAge()>=60)));
                otherHospitalOptions();
                break;
            case 4:

                otherHospitalOptions();
                break;
            case 5:
                //exit
                JOptionPane.showMessageDialog(null,"See you soon", hospital.getName(), JOptionPane.PLAIN_MESSAGE);
                break;
        }


/*
        if(JOptionPane.showConfirmDialog(null,"The "+workShift+" shift is over.\n Do you want to pay the salary to the hospital workers?",hospital.getName(),JOptionPane.YES_NO_OPTION)==JOptionPane.YES_OPTION){
             hospital.getWorkers().forEach(Worker::bePaid);
        }
*/

    }


}
