package com.solvd.hospital;

import com.solvd.hospital.application.EmmergencyApp;
import com.solvd.hospital.utilities.HospitalNumbers;
import com.solvd.hospital.utilities.MedicalSpecialties;
import org.apache.logging.log4j.*;
import com.solvd.hospital.entities.*;
import com.solvd.hospital.utilities.WorkShift;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class App 
{

    private static final Logger logger = LogManager.getLogger("App");

    public static void main(String[] args) throws ClassNotFoundException {

        //create hospital
        Hospital hospital = new Hospital("Julia Clinic");
        String email = "ababab@jom.com";

        Address addressW=new Address();
        PhoneNumber phoneNumber1= new PhoneNumber("+34", "888-888-8888");
        PhoneNumber phoneNumber2= new PhoneNumber("+01", "222-222-2222");
        PhoneNumber phoneNumber3= new PhoneNumber("+01", "777-777-7777");

        //create Nurses
        Nurse n1 = new Nurse(1000,"James","Miller",45,new PhoneNumber(),addressW, email,130F, (byte) 2, WorkShift.MORNING);
        Nurse n2 = new Nurse(1001,"John","Garcia",54,phoneNumber2 ,addressW,email, 80F, (byte) 5, WorkShift.MORNING);
        Nurse n3 = new Nurse(1002,"Robert","Smith",55,phoneNumber1,addressW, email,70F, (byte) 3, WorkShift.MORNING);
        Nurse n4 = new Nurse(1003,"Michael","Moore",44,phoneNumber1,addressW,email, 85F, (byte) 1, WorkShift.AFTERNON);
        Nurse n5 = new Nurse(1004,"Will","Thomas",22,phoneNumber1,addressW,email, 55F, (byte) 0, WorkShift.NIGHT);
        hospital.addNurse(n1);
        hospital.addNurse(n2);
        hospital.addNurse(n3);
        hospital.addNurse(n4);
        hospital.addNurse(n5);
        hospital.addWorker(n1);
        hospital.addWorker(n2);
        hospital.addWorker(n3);
        hospital.addWorker(n4);
        hospital.addWorker(n5);

        //create doctor
        Doctor d1 = new Doctor(1005,"Mary","Clark",33,phoneNumber3,addressW,email, 170F, (byte) 5, WorkShift.MORNING);
        Doctor d2 = new Doctor(1006,"Susan","Ramirez",33,phoneNumber3,addressW, email,120F, (byte) 7, WorkShift.MORNING);
        Doctor d3 = new Doctor(1007,"Nancy","Robinson",22,phoneNumber3,addressW, email,80F, (byte) 3, WorkShift.MORNING);
        Doctor d4 = new Doctor(1008,"Michelle","Walker",77,phoneNumber3,addressW, email,90F, (byte) 4, WorkShift.NIGHT);
        hospital.addDoctor(d1);
        hospital.addDoctor(d2);
        hospital.addDoctor(d3);
        hospital.addDoctor(d4);
        hospital.addWorker(d1);
        hospital.addWorker(d2);
        hospital.addWorker(d3);
        hospital.addWorker(d4);

        //create receptionists
        Receptionist r1 = new Receptionist(1009, "Kimberly","Sanchez",18, new PhoneNumber(),addressW,email,50F,(byte)1, WorkShift.MORNING);
        Receptionist r2 = new Receptionist(1010, "Brian","Lee",21, new PhoneNumber(),addressW,email,45F,(byte)3, WorkShift.AFTERNON);
        Receptionist r3 = new Receptionist(1011, "Cynthia","Thompson", 70,new PhoneNumber(),addressW,email,75F,(byte)10, WorkShift.NIGHT);
        hospital.addReceptionist(r1);
        hospital.addReceptionist(r2);
        hospital.addReceptionist(r3);
        hospital.addWorker(r1);
        hospital.addWorker(r2);
        hospital.addWorker(r3);

        //old patients
        Address address=new Address();
        MedicalRecord m2= new MedicalRecord(2002);
        m2.setMedicalSpecialty(MedicalSpecialties.GENERAL_SURGERY);
        MedicalRecord m3= new MedicalRecord(2003);
        m3.setMedicalSpecialty(MedicalSpecialties.NEUROSURGERY);
        MedicalRecord m4= new MedicalRecord(2004);
        MedicalRecord m5= new MedicalRecord(2005);
        m5.setMedicalSpecialty(MedicalSpecialties.CARDIOLOGY);
        MedicalRecord m6= new MedicalRecord(2006);
        m6.setMedicalSpecialty(MedicalSpecialties.DERMATOLOGY);
        ArrayList<MedicalRecord> medP2= new ArrayList<>();
        medP2.add(m2);
        ArrayList<MedicalRecord> medP5= new ArrayList<>();
        medP5.add(m5);
        ArrayList<MedicalRecord> medP6= new ArrayList<>();
        medP6.add(m6);
        Patient p2 = new Patient(2002,"David","King",92,new PhoneNumber(),new Address(),email, medP2);
        Patient p3 = new Patient(2003,"Matthew","Wright",30,new PhoneNumber(),new Address(),email,new ArrayList<MedicalRecord>());
        Patient p4= new Patient(2004,"Steven","Scott",70,new PhoneNumber(),new Address(),email, new ArrayList<>());
        Patient p5 = new Patient(2005,"Emily","Torres",66,new PhoneNumber(),new Address(),email,medP5);
        Patient p6 = new Patient(2006,"Melissa","Adams",34,new PhoneNumber(),new Address(),email,medP6);
        hospital.addPatient(p2);
        hospital.addPatient(p3);
        hospital.addPatient(p4);
        hospital.addPatient(p5);
        hospital.addPatient(p6);

        //creatable - my own
        List<Bed> bed = hospital.createHospitalBeds(()->{
            List<Bed> beds = new ArrayList<>();
            for(int i =1; i<=HospitalNumbers.NUM_BED.getValue(); i++){
                beds.add(new Bed(i));
            }
            return beds;
        });

        logger.info(bed);
        // bed.forEach(Bed -> System.out.println(Bed.getId()));
        hospital.setBeds(bed);




        //labs creation
        List<Lab> labsHospital = new ArrayList<>();
        IntStream.range(1,HospitalNumbers.NUM_LAB.getValue()+1)
                .forEach(x->labsHospital.add(new Lab(x)));
        hospital.setLabs(labsHospital);
        // logger.info("labs:");
        //  hospital.getLabs().forEach(Lab -> logger.info(Lab.getId()));


        //OR creation
        List<OperatingRoom> or = new ArrayList<>();
        IntStream.range(1,HospitalNumbers.NUM_OR.getValue()+1)
                .forEach(x-> or.add(new OperatingRoom(x)));
        hospital.setOperatingRooms(or);


        EmmergencyApp emmergencyApp=new EmmergencyApp (hospital,WorkShift.MORNING);
        emmergencyApp.otherHospitalOptions();

        emmergencyApp.patientsFirstLine();
        logger.info("End Hospital WF");



        /// lambda:
        logger.info("Starts Lambda Functions:");

        logger.info("Workers with name starts with J");
        logger.info(hospital.printPersonFiter((Worker worker) -> {
            if(worker.getName().startsWith("J")){
                return true;
            }
            return false;
        }));


        //Consumer
        Lab lab = new Lab(1);
        logger.info("resutls");
        lab.printLambda((Integer res) ->{
            logger.info(res);
        });


        //Function
        int result = n1.treatmentResult((Nurse::getAvility));

        int result2 = n1.treatmentResult((Nurse nurse) ->{
            return nurse.getAvility() * 2;
        });

        logger.info(result+ " "+result2);




    }

}
