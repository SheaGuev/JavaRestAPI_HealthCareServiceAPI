package com.myhealth.healthcaresystem_restapi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Patient extends Person {
    private List<MedicalRecord> medicalHistory;
    private String currentHealthStatus;
    private List<Doctor> doctors; // New field to store associated doctors
    private List<Integer> doctorIds;


    // Constructor
    @JsonCreator
    public Patient(@JsonProperty("name") String name,
                   @JsonProperty("contactInformation") String contactInformation,
                   @JsonProperty("address") String address,
                   @JsonProperty("medicalHistory") List<MedicalRecord> medicalHistory,
                   @JsonProperty("currentHealthStatus") String currentHealthStatus,
                   @JsonProperty("doctorIds") List<Integer> doctorIds,
                   @JsonProperty("id") int id) {
        super(name, contactInformation, address, id);
        this.medicalHistory = medicalHistory;
        this.currentHealthStatus = currentHealthStatus;
        this.doctorIds = doctorIds;
        this.doctors = getDoctorsDetails(doctorIds);
    }
    
    //Finding doctor details by id
    private List<Doctor> getDoctorsDetails(List<Integer> doctorIds) {
        DoctorDAO doctorDao = new DoctorDAO();
        if (doctorIds != null){
        return doctorIds.stream()
                        .map(doctorId -> {
                            Doctor doctor = doctorDao.getDoctorById(doctorId);
//                            doctor.setPatients(null); // Set the patients list to null
                            return doctor;
                        })
                        .collect(Collectors.toList());
        }else{
            return new ArrayList<>();
        }
    }

    public void doctorSerialization() {
        if (this.doctors != null) {
            List<Doctor> copiedDoctors = new ArrayList<>();
            for (Doctor record : this.doctors) {
                Doctor copiedRecord = new Doctor(record.getName(), record.getContactInformation(), record.getAddress(), record.getSpecialisation(), record.getPatientsIds(), record.getId());
                copiedRecord.setPatients(null);
                copiedDoctors.add(copiedRecord);
            }
            this.doctors = copiedDoctors;
        }
    }
    
    //public Patient(String name, String contactInformation, String address,
    //               List<MedicalRecord> medicalHistory, String currentHealthStatus, int id) {
    //    super(name, contactInformation, address, id);
    //    this.medicalHistory = medicalHistory;
    //   this.currentHealthStatus = currentHealthStatus;
    //}

    public void updateMedicalHistory(MedicalRecord medicalRecord) {
        medicalHistory.add(medicalRecord);
        prepareForSerialization();
    }

    public void prepareForSerialization() {
        if (this.medicalHistory != null) {
            List<MedicalRecord> copiedMedicalHistory = new ArrayList<>();
            for (MedicalRecord record : this.medicalHistory) {
                MedicalRecord copiedRecord = new MedicalRecord(record);
                copiedRecord.setPatients(null);
                copiedMedicalHistory.add(copiedRecord);
            }
            this.medicalHistory = copiedMedicalHistory;
        }
    }

    // Getters and Setters
    public List<MedicalRecord> getMedicalHistory() {
        return medicalHistory;
    }

    public void setMedicalHistory(List<MedicalRecord> medicalHistory) {
        this.medicalHistory = medicalHistory;
    }

    public String getCurrentHealthStatus() {
        return currentHealthStatus;
    }

    public void setCurrentHealthStatus(String currentHealthStatus) {
        this.currentHealthStatus = currentHealthStatus;
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
//        doctorSerialization();//removes patients from doctor details to prevert circular reference
    }

    public void removeMedicalRecord(int recordId) {//delete medical record from list
        this.medicalHistory = this.medicalHistory.stream()
                .filter(record -> record.getId() != recordId)
                .collect(Collectors.toList());
    }

    public void sendDetailsToDoctors() {
        DoctorDAO doctorDao = new DoctorDAO();
        for (Doctor doctor : this.getDoctors()) {
            int doctorId = doctor.getId();
            Doctor ogDoctor = doctorDao.getDoctorById(doctorId);
            List<Integer> patientIds = ogDoctor.getPatientsIds();
            if (patientIds == null) {
                patientIds = new ArrayList<>();
                ogDoctor.setPatients(patientIds);
            }
            patientIds.add(this.getId());
            ogDoctor.setPatients(patientIds);
            ogDoctor.patientSerialization();
        }
    }

    public void removeFromDoctors() {
        DoctorDAO doctorDao = new DoctorDAO();
        for (Doctor doctor : this.getDoctors()) {
            int doctorId = doctor.getId();
            Doctor ogDoctor = doctorDao.getDoctorById(doctorId);
            List<Integer> patientIds = ogDoctor.getPatientsIds();
            if (patientIds != null) {
                patientIds.remove(Integer.valueOf(this.getId())); // Remove the patient's ID
//                doctor.setPatients(patientIds);
            }
        }
    }

   
}
