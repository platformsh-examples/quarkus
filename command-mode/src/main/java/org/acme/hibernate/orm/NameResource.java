package org.acme.hibernate.orm;

import io.quarkus.panache.common.Sort;
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

@Path("names")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class NameResource {

    @GET
    public List<NameAnalysis> get() {
        return NameAnalysis.listAll(Sort.ascending("name"));
    }

    @GET
    @Path("{id}")
    public NameAnalysis getSingle(@PathParam Long id) {

        NameAnalysis entity = NameAnalysis.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Name with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(NameAnalysis nameAnalysis) {
        nameAnalysis.persist();
        return Response.ok(nameAnalysis).status(Response.Status.CREATED).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public NameAnalysis update(@PathParam Long id, NameAnalysis nameAnalysis) {

        NameAnalysis entity = NameAnalysis.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Name with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
        }

        entity.name = nameAnalysis.name;
        entity.executed = false;
        entity.persist();

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        boolean deleted = NameAnalysis.deleteById(id);
        if (deleted) {
            return Response.status(Response.Status.NO_CONTENT).build();
        }
        throw new WebApplicationException("Name with id of " + id + " does not exist.", Response.Status.NOT_FOUND);
    }

}
