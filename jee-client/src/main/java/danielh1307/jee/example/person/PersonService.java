package danielh1307.jee.example.person;

import javax.ejb.Remote;

@Remote
public interface PersonService {

	public Person addPerson(Person person) throws Exception;
	
	public Person updatePerson(Person person) throws Exception;
	
	public Person getPerson(int id);
	
	public void deletePerson(int id);
	
	public Person inactivatePerson(int id);
	
}
