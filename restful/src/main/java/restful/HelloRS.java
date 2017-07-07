package restful;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import command.Person;
import dao.PersonDao;

import org.apache.log4j.Logger;
@Path("/hello")
//@Produces(MediaType.APPLICATION_JSON)
public class HelloRS {
	
	static Logger log = Logger.getLogger(HelloRS.class.getName());
	
	private PersonDao personDao;
	
	@GET
	@Produces("text/plain")
	public String sayHelloWorld() throws Exception {
		log.debug("Running HelloRS sayHelloWorld()....");
		
		Person person = personDao.getAllPerson();
		
		return "Hello world" + person.getName();
	}   

	//    @GET
	//    @Path("/{name}")
	//    public String sayHello(@PathParam("name") String name) {
	//        return "Hello, " + name;
	//    }

	@GET
	@Path("/world")
	@Produces({ MediaType.APPLICATION_JSON })
	public Person getPersons()
	{	

		Person tracy = new Person();
		tracy.setAge("20");
		tracy.setName("Tracy");
		return tracy;
	}
	
	@GET
	@Path("/{name}")
	@Produces({ MediaType.APPLICATION_JSON })
	public Person getPersonsTest(@PathParam("name") String name)
	{	

		Person tracy = new Person();
		tracy.setAge("20");
		tracy.setName(name);
		return tracy;
	}
}
