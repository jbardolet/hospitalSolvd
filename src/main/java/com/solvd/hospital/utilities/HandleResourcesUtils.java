package com.solvd.hospital.utilities;


import com.solvd.hospital.entities.*;
import com.solvd.hospital.utilities.generics.HospitalResourcesUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class HandleResourcesUtils {

    private static final Logger logger = LogManager.getLogger("HandleResources");
    private Hospital hospital;
    private WorkShift workShift;

    public HandleResourcesUtils(Hospital hospital, WorkShift workShift) {
        this.hospital = hospital;
        this.workShift = workShift;
    }

    public HandleResourcesUtils() {
    }

    public WorkShift getWorkShift() {
        return workShift;
    }

    public void setWorkShift(WorkShift workShift) {
        this.workShift = workShift;
    }

    public Hospital getHospital() {
        return hospital;
    }


    //The next 4 methods, indicate the first available hospital resource of each type. if no resource is available, returns null.
    public Bed firstBed(){

        Bed bed = HospitalResourcesUtils.firstResource(hospital.getBeds());

        if(bed == null){
            logger.error("No bet available");
        }
        return bed;
    }
    public Lab firstLab(){
        Lab lab = HospitalResourcesUtils.firstResource(hospital.getLabs());
        if(lab== null){
            logger.error("No lab available");
        }
        return lab;

    }
    public OperatingRoom firstOR(){
        OperatingRoom or = HospitalResourcesUtils.firstResource(hospital.getOperatingRooms());
        if(or==null){
            logger.error("No Operating Room available");
        }
        return or;
    }
    public Nurse firstNurse(){
        Nurse nurse = hospital.getNurses().firstWorker(workShift);
        if(nurse==null){
            logger.error("No nurses available");
        }
        return nurse;
    }
    public Doctor firstDoctor(){

        Doctor doctor = hospital.getDoctors().firstWorker(workShift);
        if(doctor==null){
            logger.error("No doctors available");
        }
        return doctor;
    }

    public Receptionist firstReceptionist(){
        Receptionist receptionist = hospital.getReceptionists().firstWorker(workShift);
        if(receptionist==null){
            logger.error("No receptionists available");
        }
        return receptionist;
    }

}

