package restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
@Path("/hello")
public class HelloRS {

    @GET
    @Produces("text/plain")
    public String sayHelloWorld() {
        return "Hello world";
    }   
 
    @GET
    @Path("/{name}")
    public String sayHello(@PathParam("name") String name) {
        return "Hello, " + name;
    }
}
