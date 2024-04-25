/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;
import org.glassfish.jersey.server.ResourceConfig;


/**
 *
 * @author sheas
 */
public class restRegister extends ResourceConfig{
    public restRegister() {
 register(PatientResource.class);
 register(DoctorResource.class);
 register(AppointmentResource.class);
 register(BillingResource.class);
 register(MedicalRecordResource.class);
 register(PrescriptionResource.class);

 }
}
