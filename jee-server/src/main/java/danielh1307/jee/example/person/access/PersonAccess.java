package danielh1307.jee.example.person.access;

import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.util.access.GenericAccess;
import danielh1307.jee.example.vehicle.access.VehicleAccess;

@Local
@Stateless
@TransactionAttribute(TransactionAttributeType.MANDATORY)
public class PersonAccess {

	@EJB
	private GenericAccess access;

	@EJB
	private VehicleAccess vehicleAccess;

	public Person get(int id) {
		Person p = access.get(id, Person.class);
		return initializeObjects(p);
	}

	public Person getPersonByAddress(int addressId) {
		Map<String, Object> parameters = new HashMap<>();
		parameters.put("addressId", addressId);
		return initializeObjects((Person) access
				.singleResultQuery("SELECT p FROM Person p WHERE p.adresse.id LIKE :addressId", parameters));
	}
	
	public void delete(int id) {
		access.delete(access.get(id, Person.class));
	}

	private Person initializeObjects(Person p) {
		if (p != null) {
			// load lazy object
			p.getVehicles().size();
		}
		return p;
	}
}
