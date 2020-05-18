package org.acme.hibernate.orm;

import org.jboss.logging.Logger;

import javax.json.Json;
import javax.json.JsonObjectBuilder;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ErrorMapper  implements ExceptionMapper<Exception> {

    private static final Logger LOGGER = Logger.getLogger(ErrorMapper.class.getName());

    @Override
    public Response toResponse(Exception exception) {
        LOGGER.error("Failed to handle request", exception);

        int code = 500;
        if (exception instanceof WebApplicationException) {
            code = ((WebApplicationException) exception).getResponse().getStatus();
        }

        JsonObjectBuilder entityBuilder = Json.createObjectBuilder()
                .add("exceptionType", exception.getClass().getName())
                .add("code", code);

        if (exception.getMessage() != null) {
            entityBuilder.add("error", exception.getMessage());
        }

        return Response.status(code)
                .entity(entityBuilder.build())
                .build();
    }

}