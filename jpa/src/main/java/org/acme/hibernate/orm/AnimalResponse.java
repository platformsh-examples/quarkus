package org.acme.hibernate.orm;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.persistence.EntityManager;
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
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

import org.jboss.logging.Logger;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

@Path("animals")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AnimalResponse {

    @Inject
    EntityManager entityManager;

    @GET
    public Animal[] get() {
        return entityManager.createNamedQuery("Animal.findAll", Animal.class)
                .getResultList().toArray(new Animal[0]);
    }

    @GET
    @Path("{id}")
    public Animal getSingle(@PathParam Integer id) {
        Animal entity = entityManager.find(Animal.class, id);
        if (entity == null) {
            throw new WebApplicationException("Animal with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Animal animal) {
        if (animal.getId() != null) {
            throw new WebApplicationException("Id was invalidly set on request.", 422);
        }

        entityManager.persist(animal);
        return Response.ok(animal).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Animal update(@PathParam Integer id, Animal animal) {
        if (animal.getName() == null) {
            throw new WebApplicationException("Fruit Name was not set on request.", 422);
        }

        Animal entity = entityManager.find(Animal.class, id);

        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }

        entity.setName(animal.getName());

        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Integer id) {
        Animal entity = entityManager.getReference(Animal.class, id);
        if (entity == null) {
            throw new WebApplicationException("Fruit with id of " + id + " does not exist.", 404);
        }
        entityManager.remove(entity);
        return Response.status(204).build();
    }


}
