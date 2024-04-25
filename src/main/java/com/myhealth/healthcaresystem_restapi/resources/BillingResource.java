/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.myhealth.healthcaresystem_restapi.resources;

/**
 *
 * @author sheas
 */

import com.myhealth.healthcaresystem_restapi.Billing;
import com.myhealth.healthcaresystem_restapi.dao.BillingDAO;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/billings")
public class BillingResource {
    private BillingDAO billingDao = new BillingDAO();

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllBillings() {
        try {
            return Response.ok(billingDao.getAllBillings()).build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    @GET
    @Path("/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getBillingById(@PathParam("id") int id) {
        try {
            return Response.ok(billingDao.getBillingById(id)).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addBilling(Billing billing) {
        try {
            Billing newBilling = billingDao.addBilling(billing);
            return Response.status(Response.Status.CREATED).entity(newBilling).build();
        } catch (Exception e) {
            return Response.status(Response.Status.BAD_REQUEST).entity(e.getMessage()).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response updateBilling(@PathParam("id") int id, Billing billing) {
        try {
            Billing updatedBilling = billingDao.updateBilling(id, billing);
            return Response.ok(updatedBilling).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response deleteBilling(@PathParam("id") int id) {
        try {
            billingDao.deleteBilling(id);
            return Response.status(Response.Status.NO_CONTENT).build();
        } catch (WebApplicationException e) {
            return Response.status(e.getResponse().getStatus()).entity(e.getMessage()).build();
        }
    }
}
