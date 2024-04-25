/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.dao;

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

    public List<Billing> getAllBillings() {
        return new ArrayList<>(billings);
    }

    public Billing getBillingById(int id) {
        return billings.stream()
                .filter(billing -> billing.getId() == id)
                .findFirst()
                .orElseThrow(() -> new WebApplicationException("Billing not found", Response.Status.NOT_FOUND));
    }

    public Billing addBilling(Billing billing) {
        billing.setId(idCounter.incrementAndGet());
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