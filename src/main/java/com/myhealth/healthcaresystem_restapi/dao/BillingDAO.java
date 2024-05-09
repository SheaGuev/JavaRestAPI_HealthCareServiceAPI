/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;
import com.myhealth.healthcaresystem_restapi.dao.AppointmentDAO;
import com.myhealth.healthcaresystem_restapi.Appointment;
/**
 *
 * @author sheas
 */

import com.myhealth.healthcaresystem_restapi.Billing;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;

public class BillingDAO {
    private static final AtomicInteger idCounter = new AtomicInteger();
    private static final List<Billing> billings = new ArrayList<>();

    private AppointmentDAO appointmentDao = new AppointmentDAO(); // Add an instance of AppointmentDAO


    public List<Billing> getAllBillings() {
        billings.forEach(Billing::updateDynamics); //update billing to show updated details
        List<Billing> updatedBillings = new ArrayList<>(billings);
//        updatedBillings.forEach(Billing::updateDynamics); //update billing to show updated details
        return updatedBillings;
//        return new ArrayList<>(billings);
    }

    public Billing getBillingById(int id) {
        Billing _billing = billings.stream()
                .filter(billing -> billing.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Billing not found", Response.Status.NOT_FOUND));
        _billing.updateDynamics();
        return _billing;
    }

    public Billing addBilling(Billing billing) {
        billing.setId(idCounter.incrementAndGet());
        billing.appointmentSerialization();
        billing.doctorSerialization();
        billing.patientSerialization();
        billings.add(billing);
        return billing;
    }

    public Billing updateBilling(int id, Billing updatedBilling) {
        Billing billing = getBillingById(id);
        if (billing != null) {
            billing.setAmount(updatedBilling.getAmount());
            billing.setStatus(updatedBilling.getStatus());
            billing.setDoctors(updatedBilling.getDoctorIds());
            billing.setPatients(updatedBilling.getPatientIds());
            billing.setAppointmentId(updatedBilling.getAppointmentId());
            Appointment appointment = appointmentDao.getAppointmentById(updatedBilling.getAppointmentId()); // Get the Appointment by its ID
            billing.setAppointment(appointment); // Set the Appointment
            billing.appointmentSerialization();
            billing.doctorSerialization();
            billing.patientSerialization();
            return billing;
        } else {
            throw new WebApplicationException("Billing not found", Response.Status.NOT_FOUND);
        }
    }

    public void deleteBilling(int id) {
        Billing billing = getBillingById(id);
        if (billing != null) {
            billings.remove(billing);
        } else {
            throw new WebApplicationException("Billing not found", Response.Status.NOT_FOUND);
        }
    }
}