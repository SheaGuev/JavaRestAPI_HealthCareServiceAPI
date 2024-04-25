/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

/**
 *
 * @author sheas
 */

import com.myhealth.healthcaresystem_restapi.Prescription;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class PrescriptionDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<Prescription> prescriptions = new ArrayList<>();

    public List<Prescription> getAllPrescriptions() {
        return new ArrayList<>(prescriptions);
    }

    public Prescription getPrescriptionById(int id) {
        return prescriptions.stream()
                .filter(prescription -> prescription.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Prescription not found", Response.Status.NOT_FOUND));
    }

    public Prescription addPrescription(Prescription prescription) {
        prescription.setId(idCounter.incrementAndGet());
        prescriptions.add(prescription);
        return prescription;
    }

    public Prescription updatePrescription(int id, Prescription updatedPrescription) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription != null) {
            prescription.setMedication(updatedPrescription.getMedication());
            prescription.setDosage(updatedPrescription.getDosage());
            prescription.setInstructions(updatedPrescription.getInstructions());
            prescription.setDuration(updatedPrescription.getDuration());
            prescription.setDoctors(updatedPrescription.getDoctorIds());
            prescription.setPatients(updatedPrescription.getPatientIds());
            return prescription;
        } else {
            throw new WebApplicationException("Prescription not found", Response.Status.NOT_FOUND);
        }
    }

    public void deletePrescription(int id) {
        Prescription prescription = getPrescriptionById(id);
        if (prescription != null) {
            prescriptions.remove(prescription);
        } else {
            throw new WebApplicationException("Prescription not found", Response.Status.NOT_FOUND);
        }
    }
}