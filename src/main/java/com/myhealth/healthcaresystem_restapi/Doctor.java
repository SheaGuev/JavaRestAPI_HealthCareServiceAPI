package com.myhealth.healthcaresystem_restapi;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import java.util.stream.Collectors;

public class Doctor extends Person {
    private String specialisation;
    private List<Patient> patients;
    private List<Integer> patientIds;

    // Constructor
    @JsonCreator
    public Doctor(@JsonProperty("name") String name, 
                  @JsonProperty("contactInformation") String contactInformation, 
                  @JsonProperty("address") String address, 
                  @JsonProperty("specialisation") String specialisation,
                  @JsonProperty("patientIds") List<Integer> patientIds,
                  @JsonProperty("id") int id) {
        super(name, contactInformation, address, id);
        this.specialisation = specialisation;
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
    public String getSpecialisation() {
        return specialisation;
    }

    public void setSpecialisation(String specialisation) {
        this.specialisation = specialisation;
    }
   
    
     public List<Patient> getPatients() {
        return patients; //get doctors reurns the array list containing the doctors objects
    }

    public List<Integer> getPatientsIds() {
        return patientIds;
    }

    public void setPatients(List<Integer> patientIds) {
        this.patientIds = patientIds;
        this.patients = getPatientsDetails(patientIds);//this sets the doctors as the new doctors from the IDs of the input
    }
}

