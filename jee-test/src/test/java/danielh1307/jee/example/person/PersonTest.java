package danielh1307.jee.example.person;

import java.time.LocalDate;

import org.junit.Test;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.exceptions.ValidationException;
import danielh1307.jee.example.test.BaseTest;
import danielh1307.jee.example.vehicle.Bike;
import danielh1307.jee.example.vehicle.BikeType;
import danielh1307.jee.example.vehicle.Car;
import junit.framework.Assert;

public class PersonTest extends BaseTest {

	@Test
	public void completePersonLifecycleTest() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		p = getPersonService().addPerson(p);

		Person p1 = getPersonService().getPerson(p.getId());
		Assert.assertNotNull(p1);
		Assert.assertEquals("Max", p1.getVorname());
		Assert.assertEquals("Meier", p1.getNachname());
		Assert.assertEquals(LocalDate.of(2000, 1, 1), p.getGeburtsdatum());
		
		// next we set the person to INAKTIV
		p1 = getPersonService().inactivatePerson(p1.getId());
		Assert.assertSame(LifeCycleType.INAKTIV, p1.getLifeCycle());
		
		Person p2 = getPersonService().getPerson(p.getId());
		Assert.assertSame(LifeCycleType.INAKTIV, p2.getLifeCycle());

		getPersonService().deletePerson(p.getId());

		Person p3 = getPersonService().getPerson(p.getId());
		Assert.assertNull(p3);

		try {
			getPersonService().deletePerson(p.getId());
			Assert.fail("call should throw an exception");
		} catch (Exception ex) {
			// expected exception
		}
	}

	@Test
	public void addInvalidPerson() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Max").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		try {
			getPersonService().addPerson(p);
			Assert.fail("An exception was expected");
		} catch (ValidationException ex) {
			// expected exceptoin
		}
	}

	@Test
	public void addPersonWithAdresse() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		Address a = new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("18").plz("8303").ort("Bassersdorf")
				.build();
		p.setAdresse(a);
		p = getPersonService().addPerson(p);

		// change address
		p.getAdresse().get().setStrasse("Hagenbuchenstrasse");
		p.getAdresse().get().setHausnummer("9");

		p = getPersonService().updatePerson(p);

		Person p1 = getPersonService().getPerson(p.getId());
		Assert.assertTrue(p1.getAdresse().isPresent());
		Assert.assertEquals("Hagenbuchenstrasse", p1.getAdresse().get().getStrasse());

		// load address
		Address a1 = getAddressService().getAddress(p1.getAdresse().get().getId());
		Assert.assertEquals(p1.getAdresse().get().getId(), a1.getId());
		Assert.assertNotNull(a1.getPerson());

	}

	@Test
	public void deletePersonWithRelations() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		p.setAdresse(new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("18").plz("8303").ort("Bassersdorf")
				.build());
		p.addVehicle(new Car("Audi", "ein neuer Audi"));
		p.addVehicle(new Bike(BikeType.Rennrad, "Ein Rennrad"));
		
		p = getPersonService().addPerson(p);
		
		// save the ids
		int personId = p.getId();
		int addressId = p.getAdresse().get().getId();
		int vehicleId1 = p.getVehicles().get(0).getId();
		int vehicleId2 = p.getVehicles().get(1).getId();
		
		getPersonService().deletePerson(personId);
		
		// now check everything is deleted
		Assert.assertNull(getPersonService().getPerson(personId));
		Assert.assertNull(getAddressService().getAddress(addressId));
		Assert.assertNull(getVehicleService().getVehicle(vehicleId1));
		Assert.assertNull(getVehicleService().getVehicle(vehicleId2));
		
		
	}
}
