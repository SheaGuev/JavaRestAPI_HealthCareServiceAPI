/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author sheas
 */
@ApplicationPath("rest")
public class resourceManager extends Application {
 @Override
 public Set<Class<?>> getClasses() {
 Set<Class<?>> classes = new HashSet<>();
 classes.add(PatientResource.class);
 classes.add(AppointmentResource.class);
 classes.add(BillingResource.class);
 classes.add(DoctorResource.class);
 classes.add(MedicalRecordResource.class);
 classes.add(PrescriptionResource.class);
 
 return classes;
 }
}
