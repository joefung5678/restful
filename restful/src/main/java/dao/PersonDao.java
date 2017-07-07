package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import command.Person;
import mysql.MysqlConnection;
import restful.HelloRS;

public class PersonDao{

	static Logger log = Logger.getLogger(HelloRS.class.getName());
	MysqlConnection mysqlConnect = new MysqlConnection();

	public Person getAllPerson() throws Exception{
		
		StringBuilder sql = new StringBuilder();
		Person person = new Person();
		ResultSet rs = null;
		
		sql.append("	SELECT * FROM PERSON ");

		try {
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());
			
			rs = statement.executeQuery(sql.toString());
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		
		while (rs.next())
		{	
			person.setAge(rs.getString("age"));;
			person.setName(rs.getString("name"));

			// print the results
			log.debug(person.getAge() +  person.getName());
		}

		return person;
	}
}
