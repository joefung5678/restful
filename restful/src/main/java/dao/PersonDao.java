package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import command.Person;
import mysql.MysqlConnection;
import restful.HelloRS;

public class PersonDao{



	public PersonDao() {

	}

	static Logger log = Logger.getLogger(HelloRS.class.getName());


	public List<Person> getAllPerson() throws Exception{

		log.debug("Running getAllPerson()....");
		MysqlConnection mysqlConnect = new MysqlConnection();
		StringBuilder sql = new StringBuilder();
		List<Person> personList = new ArrayList<Person>();
		ResultSet rs = null;

		sql.append("	SELECT * FROM PERSON ");

		try {

			log.debug("Running Try.....");
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());

			rs = statement.executeQuery(sql.toString());

			while (rs.next())
			{	
				Person person = new Person();
				person.setPersonId(rs.getInt("PERSON_ID"));
				person.setAge(rs.getString("AGE"));
				person.setName(rs.getString("NAME"));

				// print the results
				log.debug(person.toString());
				personList.add(person);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		return personList;
	}


	public Person getByName(String name) throws Exception{

		log.debug("Running getByName()....");
		MysqlConnection mysqlConnect = new MysqlConnection();
		StringBuilder sql = new StringBuilder();
		Person person = new Person();
		ResultSet rs = null;

		sql.append("	SELECT * FROM PERSON WHERE NAME = ? ");

		try {

			log.debug("Running Try.....");
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());
			statement.setString(1, name);
			rs = statement.executeQuery();

			while (rs.next())
			{	

				person.setPersonId(rs.getInt("PERSON_ID"));
				person.setAge(rs.getString("AGE"));
				person.setName(rs.getString("NAME"));

				// print the results
				log.debug(person.toString());

			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}
		return person;
	}

	public Person add(Person person) throws Exception{

		log.debug("Running add()....");
		MysqlConnection mysqlConnect = new MysqlConnection();
		StringBuilder sql = new StringBuilder();
		StringBuilder sql2 = new StringBuilder();

		sql.append("	INSERT INTO PERSON ");
		sql.append(" ( NAME, AGE ) ");
		sql.append(" VALUES ( ?, ?) ");

		sql2.append(" SELECT LAST_INSERT_ID() AS NEW_PERSON_ID");

		try {

			log.debug("Running Try.....");
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());

			statement.setString(1, person.getName());
			statement.setString(2, person.getAge());

			statement.executeUpdate();
			PreparedStatement statement2 = mysqlConnect.connection().prepareStatement(sql2.toString());
			ResultSet rs = statement2.executeQuery(sql2.toString());

			while(rs.next()){
				//log.debug(rs.getInt("NEW_PERSON_ID"));	
				person.setPersonId(rs.getInt("NEW_PERSON_ID"));
			}

			//			person.setPersonId(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return person;

	}

	public boolean isPersonExistByName(String name) throws Exception{

		log.debug("Running isPersonExistByName()....");
		MysqlConnection mysqlConnect = new MysqlConnection();
		StringBuilder sql = new StringBuilder();
		int i = 0;

		sql.append(" SELECT COUNT(1) FROM PERSON WHERE");
		sql.append(" NAME = ?");

		try {

			log.debug("Running Try.....");
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());

			statement.setString(1, name);

			ResultSet rs = statement.executeQuery();

			while(rs.next()){
				//log.debug(rs.getInt("NEW_PERSON_ID"));	
				i = rs.getInt(1);
			}

			//			person.setPersonId(rs);
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

		return (i > 0) ? true : false;
	}
	
	public void update(Person person) throws Exception{
		
		log.debug("Running update()....");
		MysqlConnection mysqlConnect = new MysqlConnection();
		StringBuilder sql = new StringBuilder();
		sql.append(" UPDATE PERSON SET");
		sql.append(" NAME = ? ");
		sql.append(" AGE = ? ");
		sql.append(" WHERE PERSON_ID = ? ");
		try{
			log.debug("Running Try...");
			PreparedStatement statement = mysqlConnect.connection().prepareStatement(sql.toString());
			
			statement.setString(1, person.getName());
			statement.setString(2, person.getAge());
			statement.setInt(3, person.getPersonId());
			
			statement.executeUpdate();
			
		}catch(SQLException e) {
			e.printStackTrace();
		} finally {
			mysqlConnect.disconnect();
		}

	}
}
