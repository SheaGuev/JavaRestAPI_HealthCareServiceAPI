/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.MedicalRecord;
import com.myhealth.healthcaresystem_restapi.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MedicalRecordDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<MedicalRecord> medicalRecords = new ArrayList<>();
    private final PatientDAO patientDao = new PatientDAO();//to manipulate the patient object

    public List<MedicalRecord> getAllMedicalRecords() {
        medicalRecords.forEach(MedicalRecord::updateDynamics); //update medical record to show updated details
        return new ArrayList<>(medicalRecords);
    }

    public MedicalRecord getMedicalRecordById(int id) {
        MedicalRecord medical = medicalRecords.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND));
        medical.updateDynamics();
        return medical;
    }

    public MedicalRecord addMedicalRecord(MedicalRecord record) {
        record.setId(idCounter.incrementAndGet()); // Note: add a try catch to verify the id of the patient
        medicalRecords.add(record);
        return record;
    }

    public MedicalRecord updateMedicalRecord(int id, MedicalRecord updatedRecord) {
        MedicalRecord record = getMedicalRecordById(id);
        if (record != null) {
            record.setDiagnoses(updatedRecord.getDiagnoses());
            record.setTreatments(updatedRecord.getTreatments());
            record.setPatientId(updatedRecord.getPatientIds());
            return record;
        } else {
            throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
        }
    }

    public void deleteMedicalRecord(int id) {
        MedicalRecord record = getMedicalRecordById(id);
        if (record != null) {
            Patient patient = patientDao.getPatientById(record.getPatientIds());
            patient.removeMedicalRecord(id); // Remove the medical record from associated patient
            medicalRecords.remove(record);
        } else {
            throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
        }
    }
}