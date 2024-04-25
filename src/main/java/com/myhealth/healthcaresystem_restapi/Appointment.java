package com.myhealth.healthcaresystem_restapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import java.util.stream.Collectors;

public class Appointment {
    private int id;
    private List<Doctor> doctors;
    private List<Patient> patients;
    private Patient patient;
    private String date;
    private String time;
    private List<Integer> doctorIds;
    private List<Integer> patientIds;



    // Constructor
    @JsonCreator
    public Appointment(@JsonProperty("id") int id, 
            @JsonProperty("doctorIds") List<Integer> doctorIds, 
            @JsonProperty("patientIds") List<Integer> patientIds, 
            @JsonProperty("date")String date, 
            @JsonProperty("time ")String time) {
        this.id = id;
        this.doctors = getDoctorsDetails(doctorIds);
        this.patients = getPatientDetails(patientIds);
        this.date = date;
        this.time = time;
    }
    
    
    //Finding doctor details by id
    private List<Doctor> getDoctorsDetails(List<Integer> doctorIds) {
        DoctorDAO doctorDao = new DoctorDAO();
        if (doctorIds != null){
        return doctorIds.stream()
                        .map(doctorId -> {
                            Doctor doctor = doctorDao.getDoctorById(doctorId);
                            doctor.setPatients(null); // Set the patients list to null
                            return doctor;
                        })
                        .collect(Collectors.toList());
        }else{
            return null;
        }
    }
    
    //method to retrieve the patient by its ID
    private List<Patient> getPatientDetails(List<Integer> patientIds) {
        PatientDAO patientDao = new PatientDAO();
        if (patientIds != null){
        return patientIds.stream()
                        .map(patientId -> {
                            Patient patient11 = patientDao.getPatientById(patientId); //changed to patient11 to avoid conflicts in class
                            patient11.setDoctors(null); // Set the patients list to null
                            return patient11;
                        }) 
                        .collect(Collectors.toList());
        }else{
            return null;
        }
    }
    
    

    // Getters and Setters
    public List<Doctor> getDoctors() {
        return doctors; //get doctors reurns the array list containing the doctors objects
    }

    public List<Integer> getDoctorIds() {
        return doctorIds;
    }

    public void setDoctors(List<Integer> doctorIds) {
        this.doctorIds = doctorIds;
        this.doctors = getDoctorsDetails(doctorIds);//this sets the doctors as the new doctors from the IDs of the input
    }
    
    public List<Patient> getPatients() {
        return patients; //get doctors reurns the array list containing the doctors objects
    }

    public List<Integer> getPatientIds() {
        return patientIds;
    }

    public void setPatients(List<Integer> patientIds) {
        this.patientIds = patientIds;
        this.patients = getPatientDetails(patientIds);//this sets the doctors as the new doctors from the IDs of the input
    }
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}

