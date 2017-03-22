package danielh1307.jee.example.address.access;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.person.access.PersonAccess;
import danielh1307.jee.example.util.access.GenericAccess;

@Local
@Stateless
public class AddressAccess {

	@EJB
	private GenericAccess access;

	@EJB
	private PersonAccess personAccess;

	public Address get(int id) {
		Address a = access.get(id, Address.class);

		// the person object is not set on the address, we have to do this
		// ourselves
		// a.setPerson(personAccess.getPersonByAddress(a.getId()));

		return a;
	}

}
