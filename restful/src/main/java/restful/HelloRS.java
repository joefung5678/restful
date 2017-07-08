package restful;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import command.Person;
import dao.PersonDao;
@Path("/hello")
//@Produces(MediaType.APPLICATION_JSON)
public class HelloRS {
	
	static Logger log = Logger.getLogger(HelloRS.class.getName());
	
	private PersonDao personDao = new PersonDao();
	
	@GET
	@Produces("text/plain")
	public String sayHelloWorld() throws Exception {
		log.debug("Running HelloRS sayHelloWorld()....");
		//MysqlConnection connection = new MysqlConnection();
		//connection.connection();
		List<Person> personList = this.personDao.getAllPerson();
		//connection.disconnect();
		
		for(Person person : personList){
			log.debug(person.toString());
		}
		return "Hello world"; //+ person.getName();
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
	@POST
	@Path("/add")
	@Produces({ MediaType.APPLICATION_JSON })
	//@Consumes(MediaType.APPLICATION_FORM_URLENCODED)
	public Person add(@QueryParam("name") String name, @QueryParam("age") String age) throws Exception{
		
		log.debug("Running HelloRS add()....");
		
		Person person = new Person();

		person.setAge(age);
		person.setName(name);
		
		this.personDao.add(person);
		
		log.debug(person.toString());
		
		return person;
		
	}
	
	@GET
	@Path("/get")
	@Produces({MediaType.APPLICATION_JSON})
	public Person getByName(@QueryParam("name") String name) throws Exception{
		
		log.debug("Running HelloRS getByName()...");
		Person person = new Person();
		
		if(this.personDao.isPersonExistByName(name)){
			
			person = this.personDao.getByName(name);	
		}
		
		return person;
		
	}
	
	@POST
	@Path("/update")
	@Produces({MediaType.APPLICATION_JSON})
	public void update(@QueryParam("name") String name, @QueryParam("age") String age) throws Exception{
		
		log.debug("Running HelloRS update");
		

		
		if(!this.personDao.isPersonExistByName(name)){
			// ADD
			log.debug("Running ADD...");
			Person person = new Person();
			person.setAge(age);
			person.setName(name);

			this.personDao.add(person);
		}else{
			// UPDATE
			log.debug("Running UPDATE...");
			Person person = this.personDao.getByName(name);
			person.setAge(age);
			person.setName(name);
			this.personDao.update(person);
		}
			
	}
	
}
