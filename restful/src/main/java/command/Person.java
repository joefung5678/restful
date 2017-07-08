package command;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Person {
	Integer personId;
	String age;
	String name;
	
	
	public Integer getPersonId() {
		return personId;
	}
	public void setPersonId(Integer personId) {
		this.personId = personId;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Person [personId=" + personId + ", age=" + age + ", name=" + name + "]";
	}
	
	
	
}
