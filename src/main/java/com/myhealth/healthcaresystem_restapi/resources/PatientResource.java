/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.Patient;
import com.myhealth.healthcaresystem_restapi.dao.PatientDAO;
import java.util.List;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/patients")
public class PatientResource {
    private final PatientDAO patientDao = new PatientDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPatients() {
        try {
            List<Patient> patients = patientDao.getAllPatients();
            return Response.ok(patients).build();
        } catch (Exception e) {
         
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
            
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPatientById(@PathParam("id") int id) {
        try {
            Patient patient = patientDao.getPatientById(id);
            return Response.ok(patient).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPatient(Patient patient) {
        try {
            Patient newPatient = patientDao.addPatient(patient);
            
            return Response.status(Response.Status.CREATED).entity(newPatient).build();
            
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePatient(@PathParam("id") int id, Patient patient) {
        try {
            Patient updatedPatient = patientDao.updatePatient(id, patient);
            return Response.ok(updatedPatient).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePatient(@PathParam("id") int id) {
        try {
            patientDao.deletePatient(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
