package com.solvd.hospital.entities;

import com.solvd.hospital.interfaces.Creatable;
import com.solvd.hospital.interfaces.Printable;
import com.solvd.hospital.utilities.HospitalNumbers;
import com.solvd.hospital.utilities.generics.WorkerUtils;

import java.util.*;
import java.util.function.Predicate;


public class Hospital {


    private String name;
    private List<Worker> workers;
    private List<Bed> beds;
    private WorkerUtils<Doctor> doctors;
    private WorkerUtils<Nurse> nurses;
    private WorkerUtils<Receptionist> receptionists;
    private Set<Patient> oldPatients;
    private List<Lab> labs;
    private List <OperatingRoom> operatingRooms;

    public Hospital(String name) {
        this.name = name;
        beds= new ArrayList<>();
       // createbeds((ArrayList<Bed>) beds);
        workers = new ArrayList<>();
        doctors = new WorkerUtils<>();
        nurses = new WorkerUtils<>();
        receptionists = new WorkerUtils<>();
        oldPatients = new HashSet<>();
        labs= new ArrayList<>();
        //createLabs((ArrayList<Lab>) labs);
        operatingRooms = new ArrayList<>();
       // createOR((ArrayList<OperatingRoom>)operatingRooms);

    }

    public Hospital() {
    }


    public void setBeds(List<Bed> beds) {
        this.beds = beds;
    }

    public void setOperatingRooms(List<OperatingRoom> operatingRooms) {
        this.operatingRooms = operatingRooms;
    }

    public void setLabs(List<Lab> labs) {
        this.labs = labs;
    }


    public List <Bed> getBeds() {
        return beds;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Worker> getWorkers() {
        return workers;
    }


    //add item in the collections (next 5 methods)
    public void addWorker(Worker worker){
        workers.add(worker);
    }
    public void addDoctor (Doctor doctor){
        doctors.add(doctor);
    }
    public void addNurse (Nurse nurse){
        nurses.add(nurse);
    }
    public void addPatient(Patient patient){
        oldPatients.add(patient);
    }
    public void addReceptionist(Receptionist receptionist){
        receptionists.add(receptionist);
    }

    @Override
    public String toString(){
        return "Name: "+name;
    }

    public WorkerUtils<Doctor> getDoctors() {
        return doctors;
    }

    public WorkerUtils<Nurse> getNurses() {
        return nurses;
    }

    public WorkerUtils<Receptionist> getReceptionists() {
        return receptionists;
    }

    public Set<Patient> getOldPatients() {
        return oldPatients;
    }

    public List<Lab> getLabs() {
        return labs;
    }

    public List <OperatingRoom> getOperatingRooms() {
        return operatingRooms;
    }

    public List<Worker> printPersonFiter(Predicate<Worker> filterWorkers){
        List<Worker> workersFiltered = new ArrayList<>();
        for(Worker worker: this.workers){
            if(filterWorkers.test(worker)){
                workersFiltered.add(worker);
            }
        }
        return workersFiltered;

    }
    public void printAllWorkers(Printable<Worker> printable){
        printable.print();
    }

    public List<Bed> createHospitalBeds(Creatable<Bed> creatable) {

        return creatable.create();


    }
}

