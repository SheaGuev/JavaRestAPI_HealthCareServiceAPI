/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.Patient;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class PatientDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<Patient> patients = new ArrayList<>();

    public List<Patient> getAllPatients() {
        return patients;
    }

    public Patient getPatientById(int id) {
        return patients.stream()
                .filter(patient -> patient.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Patient not found", Response.Status.NOT_FOUND));
    }

    //Error encountered, debugging post method
    public Patient addPatient(Patient patient) {
        try {
            // Increment the ID and set it to the patient
            patient.setId(idCounter.incrementAndGet());

            // Attempt to add the patient to the list
            patients.add(patient);

            // If adding is successful, return the patient
            return patient;
        } catch (Exception e) {

           throw new RuntimeException("Failed to add patient", e);
        }
    }

    public Patient updatePatient(int id, Patient patient) {
        Patient existingPatient = getPatientById(id);
        if (existingPatient != null) {
            existingPatient.setName(patient.getName());
            existingPatient.setContactInformation(patient.getContactInformation());
            existingPatient.setAddress(patient.getAddress());
            existingPatient.setMedicalHistory(patient.getMedicalHistory());
            existingPatient.setCurrentHealthStatus(patient.getCurrentHealthStatus());
            existingPatient.setDoctors(patient.getDoctorIds());
            return existingPatient;
        } else {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
    }

    public void deletePatient(int id) {
        Patient patient = getPatientById(id);
        if (patient != null) {
            patients.remove(patient);
        } else {
            throw new WebApplicationException("Patient not found", Response.Status.NOT_FOUND);
        }
    }
}

