package danielh1307.jee.example.vehicle;

import java.time.LocalDate;

import org.junit.Test;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class VehicleTest extends BaseTest {

	@Test
	public void testAddVehicle() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(1990, 1, 1))
				.build();
		p.addVehicle(new Car("Audi", "ein neues Auto"));
		p.addVehicle(new Bike(BikeType.Rennrad, "Stevens Stelvio"));
		p = getPersonService().addPerson(p);

		Person p1 = getPersonService().getPerson(p.getId());
		Assert.assertEquals(2, p1.getVehicles().size());

		Vehicle v1 = getVehicleService().getVehicle(p1.getVehicles().get(0).getId());
		Assert.assertNotNull(v1);

	}

	@Test
	public void testUpdateVehicle() throws Exception {
		// first we have to create a person
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(1990, 1, 1))
				.build();
		Car c = new Car("Audi", "ein neues Auto");
		c.setOwner(p);
		c = (Car) getVehicleService().addVehicle(c);

		int personId = c.getOwner().getId();

		// next we load the person
		Person p1 = getPersonService().getPerson(personId);
		Assert.assertNotNull(p1);
		Assert.assertEquals("Max", p1.getVorname());
		Assert.assertEquals(1, p1.getVehicles().size());

		// add a new vehicle
		Bike b = new Bike(BikeType.Rennrad, "Stevens Stelvio");
		p1.addVehicle(b);

		// and we update this person
		getPersonService().updatePerson(p1);
		p1 = getPersonService().getPerson(personId);
		Assert.assertEquals(2, p1.getVehicles().size());

		// other way to persist a vehicle
		Bike newBike = new Bike(BikeType.Mountainbike, "Over the rock");
		newBike.setOwner(p1);
		// this is a bit tricky here - we have to call update() because some
		// parts of the object relations (here: p1) is already persisted
		getVehicleService().updateVehicle(newBike);

		Person p2 = getPersonService().getPerson(personId);
		Assert.assertEquals(3, p2.getVehicles().size());

		Vehicle vehicleToRemove = p2.getVehicles().get(2);
		p2.removeVehicle(vehicleToRemove);
		Assert.assertEquals(2, p2.getVehicles().size());
		getPersonService().updatePerson(p2);

		// By default, it is not possible to delete a vehicle by just removing
		// it from the list. This behavior must be implemented in the according
		// service.
		Person p3 = getPersonService().getPerson(personId);
		Assert.assertEquals(3, p3.getVehicles().size());
	}

}
