package com.myhealth.healthcaresystem_restapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecord {
    private int id;
    private List<String> diagnoses;
    private List<String> treatments;
    private List<Patient> patients;
    private List<Integer> patientIds;
    
    // Constructor
    @JsonCreator
    public MedicalRecord(@JsonProperty("id") int id, 
                         @JsonProperty("diagnoses") List<String> diagnoses, 
                         @JsonProperty("treatments") List<String> treatments,
                         @JsonProperty("patientIds") List<Integer> patientIds) {
        this.id = id;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.patients = getPatientsDetails(patientIds);
    }
    
    //method to retrieve the patient by its ID
    private List<Patient> getPatientsDetails(List<Integer> patientIds) {
        PatientDAO patientDao = new PatientDAO();
        if (patientIds != null){
        return patientIds.stream()
                        .map(patientId -> {
                            Patient patient = patientDao.getPatientById(patientId);
                            patient.setDoctors(null); // Set the patients list to null
                            return patient;
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

    public List<String> getDiagnoses() {
        return diagnoses;
    }

    public void setDiagnoses(List<String> diagnoses) {
        this.diagnoses = diagnoses;
    }

    public List<String> getTreatments() {
        return treatments;
    }

    public void setTreatments(List<String> treatments) {
        this.treatments = treatments;
    }
    
    public List<Patient> getPatients() {
        return patients; //get doctors reurns the array list containing the doctors objects
    }

    public List<Integer> getPatientIds() {
        return patientIds;
    }

    public void setPatients(List<Integer> patientIds) {
        this.patientIds = patientIds;
        this.patients = getPatientsDetails(patientIds);//this sets the doctors as the new doctors from the IDs of the input
    }
}
