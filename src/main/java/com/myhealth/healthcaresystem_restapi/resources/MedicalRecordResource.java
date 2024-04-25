/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */

import com.myhealth.healthcaresystem_restapi.MedicalRecord;
import com.myhealth.healthcaresystem_restapi.dao.MedicalRecordDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/medicalRecords")
public class MedicalRecordResource {
    private MedicalRecordDAO medicalRecordDao = new MedicalRecordDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllMedicalRecords() {
        try {
            return Response.ok(medicalRecordDao.getAllMedicalRecords()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getMedicalRecordById(@PathParam("id") int id) {
        try {
            return Response.ok(medicalRecordDao.getMedicalRecordById(id)).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addMedicalRecord(MedicalRecord medicalRecord) {
        try {
            MedicalRecord newRecord = medicalRecordDao.addMedicalRecord(medicalRecord);
            return Response.status(Response.Status.CREATED).entity(newRecord).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateMedicalRecord(@PathParam("id") int id, MedicalRecord medicalRecord) {
        try {
            MedicalRecord updatedRecord = medicalRecordDao.updateMedicalRecord(id, medicalRecord);
            return Response.ok(updatedRecord).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteMedicalRecord(@PathParam("id") int id) {
        try {
            medicalRecordDao.deleteMedicalRecord(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}