package com.myhealth.healthcaresystem_restapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import java.util.stream.Collectors;

public class Prescription {
    private int id;
    private String medication;
    private String dosage;
    private String instructions;
    private int duration;
    private Doctor doctor;
    private Patient patient;
    private List<Integer> doctorIds;
    private List<Integer> patientIds;
    private List<Doctor> doctors;
    private List<Patient> patients;
    

    // Constructor
    @JsonCreator
    public Prescription(@JsonProperty("id") int id, 
                        @JsonProperty("medication") String medication, 
                        @JsonProperty("dosage") String dosage, 
                        @JsonProperty("instructions") String instructions,
                        @JsonProperty("doctorIds") List<Integer> doctorIds, 
                        @JsonProperty("patientIds") List<Integer> patientIds,
                        @JsonProperty("duration") int duration) {
        this.id = id;
        this.medication = medication;
        this.dosage = dosage;
        this.instructions = instructions;
        this.duration = duration;
        this.doctors = getDoctorsDetails(doctorIds);
        this.patients = getPatientDetails(patientIds);
    }
    
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
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMedication() {
        return medication;
    }

    public void setMedication(String medication) {
        this.medication = medication;
    }

    public String getDosage() {
        return dosage;
    }

    public void setDosage(String dosage) {
        this.dosage = dosage;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
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
}



