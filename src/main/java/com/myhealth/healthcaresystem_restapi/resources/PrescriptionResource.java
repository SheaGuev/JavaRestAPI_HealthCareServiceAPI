/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */


import com.myhealth.healthcaresystem_restapi.Prescription;
import com.myhealth.healthcaresystem_restapi.dao.PrescriptionDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/prescriptions")
public class PrescriptionResource {
    private PrescriptionDAO prescriptionDao = new PrescriptionDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllPrescriptions() {
        try {
            return Response.ok(prescriptionDao.getAllPrescriptions()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getPrescriptionById(@PathParam("id") int id) {
        try {
            return Response.ok(prescriptionDao.getPrescriptionById(id)).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addPrescription(Prescription prescription) {
        try {
            Prescription newPrescription = prescriptionDao.addPrescription(prescription);
            return Response.status(Response.Status.CREATED).entity(newPrescription).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updatePrescription(@PathParam("id") int id, Prescription prescription) {
        try {
            Prescription updatedPrescription = prescriptionDao.updatePrescription(id, prescription);
            return Response.ok(updatedPrescription).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deletePrescription(@PathParam("id") int id) {
        try {
            prescriptionDao.deletePrescription(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
