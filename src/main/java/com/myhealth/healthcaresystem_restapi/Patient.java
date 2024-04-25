package com.myhealth.healthcaresystem_restapi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
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
                            doctor.setPatients(null); // Set the patients list to null
                            return doctor;
                        })
                        .collect(Collectors.toList());
        }else{
            return null;
        }
    }
    
    //public Patient(String name, String contactInformation, String address,
    //               List<MedicalRecord> medicalHistory, String currentHealthStatus, int id) {
    //    super(name, contactInformation, address, id);
    //    this.medicalHistory = medicalHistory;
    //   this.currentHealthStatus = currentHealthStatus;
    //}

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
    }
   
}
