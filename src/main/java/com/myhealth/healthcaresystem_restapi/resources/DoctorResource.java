/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */
import com.myhealth.healthcaresystem_restapi.Doctor;
import com.myhealth.healthcaresystem_restapi.dao.DoctorDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/doctors")
public class DoctorResource {
    private DoctorDAO doctorDao = new DoctorDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllDoctors() {
        try {
            List<Doctor> doctors = doctorDao.getAllDoctors();
            return Response.ok(doctors).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getDoctorById(@PathParam("id") int id) {
        try {
            Doctor doctor = doctorDao.getDoctorById(id);
            return Response.ok(doctor).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addDoctor(Doctor doctor) {
        try {
            Doctor newDoctor = doctorDao.addDoctor(doctor);
            return Response.status(Response.Status.CREATED).entity(newDoctor).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateDoctor(@PathParam("id") int id, Doctor doctor) {
        try {
            Doctor updatedDoctor = doctorDao.updateDoctor(id, doctor);
            return Response.ok(updatedDoctor).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteDoctor(@PathParam("id") int id) {
        try {
            doctorDao.deleteDoctor(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
