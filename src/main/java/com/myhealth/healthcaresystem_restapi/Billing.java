package com.myhealth.healthcaresystem_restapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import java.util.stream.Collectors;

public class Billing {
    private int id;
    private double amount;
    private String status;
    private Doctor doctor;
    private Patient patient;
    private Appointment appointment;
    private List<Integer> doctorIds;
    private List<Integer> patientIds;
    private List<Doctor> doctors;
    private List<Patient> patients;

    // Constructor
    @JsonCreator
    public Billing(@JsonProperty("id") int id, 
            @JsonProperty("amount") double amount, 
            @JsonProperty("status") String status,
            @JsonProperty("doctorIds") List<Integer> doctorIds, 
            @JsonProperty("patientIds") List<Integer> patientIds,
            @JsonProperty("appointment") Appointment appointment){
        this.id = id;
        this.amount = amount;
        this.status = status;
        this.doctors = getDoctorsDetails(doctorIds);
        this.patients = getPatientDetails(patientIds);
        this.appointment = appointment;
    }
    

//methods to fill patient and doctors 
    //Finding doctor details by id
    private List<Doctor> getDoctorsDetails(List<Integer> doctorIds) {
        DoctorDAO doctorDao = new DoctorDAO();
        if (doctorIds != null){
        return doctorIds.stream()
                        .map(doctorId -> {
                            Doctor doctor11 = doctorDao.getDoctorById(doctorId);
                            doctor11.setPatients(null); // Set the patients list to null
                            return doctor11;
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
                            Patient patient11 = patientDao.getPatientById(patientId);
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

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
    
    public Doctor getDoctor() {
        return doctor;
    }

    public void setDoctor(Doctor doctor) {
        this.doctor = doctor;
    }

    public Patient getPatient() {
        return patient;
    }

    public void setPatient(Patient patient) {
        this.patient = patient;
    }
    
    public Appointment getAppointment() {
        return appointment;
    }

    public void setAppointment(Appointment appointment) {
        this.appointment = appointment;
    }
}


