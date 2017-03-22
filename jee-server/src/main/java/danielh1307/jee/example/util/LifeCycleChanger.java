package danielh1307.jee.example.util;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.lcs.AddressLcsChange;
import danielh1307.jee.example.lcs.LifeCycleChangeEvent;
import danielh1307.jee.example.lcs.PersonLcsChange;
import danielh1307.jee.example.lcs.VehicleLcsChange;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.util.access.GenericAccess;
import danielh1307.jee.example.vehicle.Vehicle;

@Stateless
public class LifeCycleChanger {
	
	@EJB
	GenericAccess access;
	
	@Inject
	@PersonLcsChange
	Event<LifeCycleChangeEvent<Person>> personLcsChangeEvent;
	
	Event<Person> personEvent;
	
	@Inject
	@AddressLcsChange
	Event<LifeCycleChangeEvent<Address>> addressLcsChangeEvent;
	
	@Inject
	@VehicleLcsChange
	Event<LifeCycleChangeEvent<Vehicle>> vehicleLcsChangeEvent;
	
	public Person inactivatePerson(int id) {
		Person p = inactivateLifeCycle(id, Person.class);
		personLcsChangeEvent.fire(new LifeCycleChangeEvent<Person>(p));
		return p;
	}
	
	public Address inactivateAddress(int id) {
		Address a = inactivateLifeCycle(id,  Address.class);
		addressLcsChangeEvent.fire(new LifeCycleChangeEvent<Address>(a));
		return a;
	}
	
	public Vehicle inactivateVehicle(int id) {
		Vehicle v = inactivateLifeCycle(id, Vehicle.class);
		vehicleLcsChangeEvent.fire(new LifeCycleChangeEvent<Vehicle>(v));
		return v;
	}

	private <T extends BaseEntity> T inactivateLifeCycle(int id, Class<T> clazz) {
		// make sure the object is attached
		T e = access.get(id, clazz);
		
		if (e.getLifeCycle() != LifeCycleType.AKTIV) {
			throw new RuntimeException(
					"The lifeCycleType of [" + e + "] is [" + e.getLifeCycle() + "] and cannot be set to INAKTIV");
		}
		e.setLifeCycle(LifeCycleType.INAKTIV);
		return e;
	}
}
