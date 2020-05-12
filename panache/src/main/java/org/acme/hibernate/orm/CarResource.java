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

@Path("cars")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CarResource {

    @GET
    public List<Car> get() {
        return Car.listAll(Sort.ascending("plate"));
    }

    @GET
    @Path("{id}")
    public Car getSingle(@PathParam Long id) {

        Car entity = Car.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Car with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Car car) {
        car.persist();
        return Response.ok(car).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Car update(@PathParam Long id, Car car) {

        Car entity = Car.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Car with id of " + id + " does not exist.", 404);
        }

        entity.setMake(car.getMake());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        boolean deleted = Car.deleteById(id);
        if (deleted) {
            return Response.status(204).build();
        }
        throw new WebApplicationException("Car with id of " + id + " does not exist.", 404);
    }

}
