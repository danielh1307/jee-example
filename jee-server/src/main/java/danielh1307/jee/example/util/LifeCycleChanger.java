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

/**
 * This is the only class from which the life cycle of any entity is changed.
 * 
 * Since the method {@link BaseEntity#setLifeCycle(LifeCycleType)} is protected, 
 * this class needs to be in the same package.
 * 
 * After the life cycle has been changed, this method fires the according
 * {@link LifeCycleChangeEvent} and the according observers are triggered.
 *
 */
@Stateless
public class LifeCycleChanger {
	
	@EJB
	private GenericAccess access;
	
	@Inject
	@PersonLcsChange
	private Event<LifeCycleChangeEvent<Person>> personLcsChangeEvent;
	
	@Inject
	@AddressLcsChange
	private Event<LifeCycleChangeEvent<Address>> addressLcsChangeEvent;
	
	@Inject
	@VehicleLcsChange
	private Event<LifeCycleChangeEvent<Vehicle>> vehicleLcsChangeEvent;
	
	/**
	 * Sets the life cycle of a person from ACTIVE to INACTIVE.
	 * 
	 * @param id id of a person with active life cycle
	 * @return person of the given id with inactive life cycle
	 */
	public Person inactivatePerson(int id) {
		Person p = inactivateLifeCycle(id, Person.class);
		personLcsChangeEvent.fire(new LifeCycleChangeEvent<Person>(p));
		return p;
	}
	
	/**
	 * Sets the life cycle of an address form ACTIVE to INACTIVE.
	 * 
	 * @param id id of an address with active life cycle
	 * @return address of the given id with inactive life cycle
	 */
	public Address inactivateAddress(int id) {
		Address a = inactivateLifeCycle(id,  Address.class);
		addressLcsChangeEvent.fire(new LifeCycleChangeEvent<Address>(a));
		return a;
	}
	
	/**
	 * Sets the life cycle of a vehicle from ACTIVE to INACTIVE.
	 * 
	 * @param id id of a vehicle with active life cycle
	 * @return vehicle of the given id with inactive life cycle
	 */
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
