/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.Appointment;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class AppointmentDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<Appointment> appointments = new ArrayList<>();

    // Method to get all appointments
    public List<Appointment> getAllAppointments() {
        appointments.forEach(Appointment::updateDynamics); //update appointment to show updated details
        List<Appointment> updatedAppointments = new ArrayList<>(appointments);
//        updatedAppointments.forEach(Appointment::updateDynamics); //update appointment to show updated details
        return updatedAppointments;
//        return new ArrayList<>(appointments);
    }

    // Method to get a single appointment by ID
    public Appointment getAppointmentById(int id) {
        Appointment appointment = appointments.stream()
                .filter(a -> a.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND));
        appointment.updateDynamics();
        return appointment;
    }

    // Method to add a new appointment
    public Appointment addAppointment(Appointment appointment) {
        appointment.setId(idCounter.incrementAndGet());
        appointment.patientSerialization();
        appointment.doctorSerialization();
        appointments.add(appointment);
        return appointment;
    }

    // Method to update an existing appointment
    public Appointment updateAppointment(int id, Appointment updatedAppointment) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointment.setDoctors(updatedAppointment.getDoctorIds());
            appointment.setPatients(updatedAppointment.getPatientIds());
            appointment.setDate(updatedAppointment.getDate());
            appointment.setTime(updatedAppointment.getTime());
            appointment.patientSerialization();
            appointment.doctorSerialization();
            return appointment;
        } else {
            throw new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND);
        }
    }

    // Method to delete an appointment
    public void deleteAppointment(int id) {
        Appointment appointment = getAppointmentById(id);
        if (appointment != null) {
            appointments.remove(appointment);
        } else {
            throw new WebApplicationException("Appointment not found", Response.Status.NOT_FOUND);
        }
    }
}