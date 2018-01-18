package de.romanroe.demos.jetty_guice_jersey.resource;

import de.romanroe.demos.jetty_guice_jersey.service.ValueService;
import com.google.inject.Inject;
import com.google.inject.servlet.RequestScoped;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

@Path("/")
@RequestScoped
public class GetNextValueResource {

    private final ValueService valueService;

    @Inject
     public GetNextValueResource(ValueService valueService) {
        this.valueService = valueService;
    }

    @GET
    @Path("getNext")
    @Produces(MediaType.TEXT_PLAIN)
    public String getNext() {
        return "2 - " + Integer.toString(this.valueService.nextValue());
    }
}
