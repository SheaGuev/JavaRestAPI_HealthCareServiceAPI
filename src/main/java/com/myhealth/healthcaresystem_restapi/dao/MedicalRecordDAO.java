/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.MedicalRecord;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class MedicalRecordDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<MedicalRecord> medicalRecords = new ArrayList<>();

    public List<MedicalRecord> getAllMedicalRecords() {
        return new ArrayList<>(medicalRecords);
    }

    public MedicalRecord getMedicalRecordById(int id) {
        return medicalRecords.stream()
                .filter(record -> record.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND));
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
            record.setPatients(updatedRecord.getPatientIds());
            return record;
        } else {
            throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
        }
    }

    public void deleteMedicalRecord(int id) {
        MedicalRecord record = getMedicalRecordById(id);
        if (record != null) {
            medicalRecords.remove(record);
        } else {
            throw new WebApplicationException("Medical record not found", Response.Status.NOT_FOUND);
        }
    }
}