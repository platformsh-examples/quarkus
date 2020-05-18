package org.acme.hibernate.orm;

import io.quarkus.panache.common.Sort;
import org.bson.types.ObjectId;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("gods")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class GodResource {

    @GET
    public List<God> get() {
        return God.listAll(Sort.ascending("name"));
    }

    @GET
    @Path("{id}")
    public God getSingle(@PathParam String id) {

        God entity = God.findById(new ObjectId(id));
        if (entity == null) {
            throw new WebApplicationException("God with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(God god) {
        god.persist();
        return Response.ok(god).status( Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public God update(@PathParam String id, God god) {

        God entity = God.findById(new ObjectId(id));
        if (entity == null) {
            throw new WebApplicationException("God with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        entity.name = god.name;
        entity.powers = god.powers;
        entity.persistOrUpdate();
        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam String id) {
        boolean deleted = God.deleteById(new ObjectId(id));
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        throw new WebApplicationException("God with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
    }

}
