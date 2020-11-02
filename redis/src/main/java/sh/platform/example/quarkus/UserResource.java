package sh.platform.example.quarkus;


import io.smallrye.mutiny.Uni;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import java.util.List;

@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class UserResource {

    @Inject
    UserService service;

    @GET
    public Uni<List<String>> keys() {
        return service.keys();
    }

    @POST
    public User create(User user) {
        service.set(user.userName, user.country);
        return user;
    }

    @GET
    @Path("/{key}")
    public User get(@PathParam("key") String key) {
        return new User(key, service.get(key));
    }

    @DELETE
    @Path("/{key}")
    public Uni<Void> delete(@PathParam("key") String key) {
        return service.del(key);
    }
}