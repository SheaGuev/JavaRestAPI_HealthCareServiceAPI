/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.Doctor;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class DoctorDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<Doctor> doctors = new ArrayList<>();

    // Retrieve all doctors
    public List<Doctor> getAllDoctors() {
        return new ArrayList<>(doctors);
    }

    // Retrieve a single doctor by ID
    public Doctor getDoctorById(int id) {
        return doctors.stream()
                .filter(doctor -> doctor.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Doctor not found", Response.Status.NOT_FOUND));
    }

    // Add a new doctor
    public Doctor addDoctor(Doctor doctor) {
        doctor.setId(idCounter.incrementAndGet());
        doctors.add(doctor);
        doctor.sendDetailsToPatients();
        doctor.patientSerialization();
        return doctor;
    }

    // Update an existing doctor
    public Doctor updateDoctor(int id, Doctor updatedDoctor) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctor.setName(updatedDoctor.getName());
            doctor.setContactInformation(updatedDoctor.getContactInformation());
            doctor.setAddress(updatedDoctor.getAddress());
            doctor.setSpecialisation(updatedDoctor.getSpecialisation());
            doctor.setPatients(updatedDoctor.getPatientsIds());
            doctor.sendDetailsToPatients();
            doctor.patientSerialization();
            return doctor;
        } else {
            throw new WebApplicationException("Doctor not found", Response.Status.NOT_FOUND);
        }
    }

    // Delete a doctor
    public void deleteDoctor(int id) {
        Doctor doctor = getDoctorById(id);

        if (doctor != null) {
            doctor.removeFromPatients(); // Remove the doctor from associated patients
            doctors.remove(doctor);


        } else {
            throw new WebApplicationException("Doctor not found", Response.Status.NOT_FOUND);
        }
    }
}