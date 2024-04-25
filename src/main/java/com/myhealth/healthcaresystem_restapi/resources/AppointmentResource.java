/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */

import com.myhealth.healthcaresystem_restapi.Appointment;
import com.myhealth.healthcaresystem_restapi.dao.AppointmentDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/appointments")
public class AppointmentResource {
    private AppointmentDAO appointmentDao = new AppointmentDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllAppointments() {
        try {
            return Response.ok(appointmentDao.getAllAppointments()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAppointmentById(@PathParam("id") int id) {
        try {
            return Response.ok(appointmentDao.getAppointmentById(id)).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addAppointment(Appointment appointment) {
        try {
            Appointment newAppointment = appointmentDao.addAppointment(appointment);
            return Response.status(Response.Status.CREATED).entity(newAppointment).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateAppointment(@PathParam("id") int id, Appointment appointment) {
        try {
            Appointment updatedAppointment = appointmentDao.updateAppointment(id, appointment);
            return Response.ok(updatedAppointment).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteAppointment(@PathParam("id") int id) {
        try {
            appointmentDao.deleteAppointment(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}