package com.myhealth.healthcaresystem_restapi;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MedicalRecord {
    private int id;
    private List<String> diagnoses;
    private List<String> treatments;
    private Patient patient;
    private int patientIds;
    
    // Constructor
    @JsonCreator
    public MedicalRecord(@JsonProperty("id") int id, 
                         @JsonProperty("diagnoses") List<String> diagnoses, 
                         @JsonProperty("treatments") List<String> treatments,
                         @JsonProperty("patientIds") int patientIds) {
        this.id = id;
        this.diagnoses = diagnoses;
        this.treatments = treatments;
        this.patientIds = patientIds;
        this.patient = getPatientDetails(patientIds);
    }

    public MedicalRecord(MedicalRecord other) {
        this.id = other.id;
        this.diagnoses = new ArrayList<>(other.diagnoses);
        this.treatments = new ArrayList<>(other.treatments);
        this.patient = other.patient;
        this.patientIds = other.patientIds;
    }


    //method to retrieve the patient by its ID
    private Patient getPatientDetails(int patientIds) {
        PatientDAO patientDao = new PatientDAO();
        if (patientIds != 0){
            Patient patient = patientDao.getPatientById(patientIds);
//            patient.setDoctors(null); // Set the patients list to null
            return patient;
        }else{
            return null;
        }
    }

    public void patientSerialization() {
        if (this.patient != null) {
            Patient copiedPatient = new Patient(this.patient.getName(), this.patient.getContactInformation(), this.patient.getAddress(), this.patient.getMedicalHistory(), this.patient.getCurrentHealthStatus(), this.patient.getDoctorIds(), this.patient.getId());
            copiedPatient.setDoctors(null);
            this.patient = copiedPatient;

    }}
    

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
    
    public Patient getPatients() {
        return patient; //get doctors reurns the array list containing the doctors objects
    }

    public int getPatientIds() {
        return patientIds;
    }

    public void setPatientId(int patientIds) {
        this.patientIds = patientIds;
        this.patient = getPatientDetails(patientIds);//this sets the doctors as the new doctors from the IDs of the input
        patientSerialization();
    }

    public void setPatients(Patient patient) {
        this.patient = patient;//this sets the doctors as the new doctors from the IDs of the input
    }


    public void updateDynamics() {
        PatientDAO patientDAO = new PatientDAO();
        if (patient != null) {

            Patient ogPatient = patientDAO.getPatientById(patient.getId());
            this.patient = ogPatient;
            this.patientSerialization();
        }

    }

}
