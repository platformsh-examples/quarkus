package org.acme.hibernate.orm;

import io.quarkus.panache.common.Sort;
import io.quarkus.runtime.StartupEvent;
import org.hibernate.search.mapper.orm.Search;
import org.jboss.resteasy.annotations.jaxrs.PathParam;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.event.Observes;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import org.jboss.resteasy.annotations.jaxrs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;
import java.util.Optional;

@Path("musics")
@ApplicationScoped
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class MusicResource {

    @Inject
    EntityManager em;

    @Transactional
    void onStart(@Observes StartupEvent ev) throws InterruptedException {
        if (Music.count() > 0) {
            Search.session(em)
                    .massIndexer()
                    .startAndWait();
        }
    }

    @GET
    public List<Music> get() {
        return Music.listAll(Sort.ascending("singer"));
    }

    @GET
    @Path("{id}")
    public Music getSingle(@PathParam Long id) {

        Music entity = Music.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Music with id of " + id + " does not exist.", 404);
        }
        return entity;
    }

    @POST
    @Transactional
    public Response create(Music music) {
        music.persist();
        return Response.ok(music).status(201).build();
    }

    @PUT
    @Path("{id}")
    @Transactional
    public Music update(@PathParam Long id, Music music) {

        Music entity = Music.findById(id);
        if (entity == null) {
            throw new WebApplicationException("Music with id of " + id + " does not exist.", 404);
        }
        entity.setSinger(music.getSinger());
        return entity;
    }

    @DELETE
    @Path("{id}")
    @Transactional
    public Response delete(@PathParam Long id) {
        boolean deleted = Music.deleteById(id);
        if (deleted) {
            return Response.status(204).build();
        }
        throw new WebApplicationException("Music with id of " + id + " does not exist.", 404);
    }

    @GET
    @Path("search")
    @Transactional
    public List<Music> searchAuthors(@QueryParam() String pattern,
                                      @QueryParam() Optional<Integer> size) {
        List<Music> authors = Search.session(em)
                .search(Music.class)
                .where(f -> pattern == null || pattern.trim().isEmpty() ? f.matchAll()
                        : f.simpleQueryString()
                        .fields("lyric", "singer", "author").matching(pattern))
                .sort(f -> f.field("singer_sort").then().field("author_sort"))
                .fetchHits(size.orElse(20));
        return authors;
    }

}
