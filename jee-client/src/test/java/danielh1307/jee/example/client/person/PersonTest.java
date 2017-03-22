package danielh1307.jee.example.client.person;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.vehicle.Bike;
import danielh1307.jee.example.vehicle.BikeType;
import danielh1307.jee.example.vehicle.Car;

public class PersonTest {

	@Test
	public void testValidPerson() {
		new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
	}

	@Test
	public void testInvalidPerson() {
		try {
			new Person.PersonBuilder().vorname(null).nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
			Assert.fail("Expected exception not thrown");
		} catch (Exception ex) {
			// expected exception
		}

		try {
			new Person.PersonBuilder().vorname("Max").nachname(" ").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
			Assert.fail("Expected exception not thrown");
		} catch (Exception ex) {
			// expected exception
		}

		try {
			new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(null).build();
			Assert.fail("Expected exception not thrown");
		} catch (Exception ex) {
			// expected exception
		}
	}

	/**
	 * Tests of 1:1 bi-directional mapping.
	 */
	@Test
	public void testPersonAddressRelation() {
		Person p1 = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		Address a1 = new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("1").plz("8000").ort("Zürich")
				.build();

		// test relation from person
		p1.setAdresse(a1);
		Assert.assertEquals(a1, p1.getAdresse().get());
		Assert.assertEquals(p1, a1.getPerson());
		
		// can I add it twice?
		p1.setAdresse(a1);
		Assert.assertEquals(a1, p1.getAdresse().get());
		Assert.assertEquals(p1, a1.getPerson());

		try {
			p1.setAdresse(null);
			Assert.fail("this call should throw an excpeption");
		} catch (RuntimeException ex) {
			// expected exception
		}
		p1.removeAdresse();
		Assert.assertFalse(p1.getAdresse().isPresent());
		Assert.assertNull(a1.getPerson());

		// test relation from address
		a1.setPerson(p1);
		Assert.assertEquals(a1, p1.getAdresse().get());
		Assert.assertEquals(p1, a1.getPerson());
		
		// can I add it twice?
		a1.setPerson(p1);
		Assert.assertEquals(a1, p1.getAdresse().get());
		Assert.assertEquals(p1, a1.getPerson());

		try {
			a1.setPerson(null);
			Assert.fail("this call should throw an exception");
		} catch (RuntimeException ex) {
			// expected exception
		}
		a1.removePerson();
		Assert.assertFalse(p1.getAdresse().isPresent());
		Assert.assertNull(a1.getPerson());

		// test re-assignment of address
		Person p2 = new Person.PersonBuilder().vorname("Heiner").nachname("Meier")
				.geburtsdatum(LocalDate.of(2000, 1, 1)).build();
		p1.setAdresse(a1);

		try {
			p2.setAdresse(a1);
			Assert.fail("this call should throw an exception");
		} catch (RuntimeException ex) {
			// expected excpeption
		}
		Assert.assertTrue(p1.getAdresse().isPresent());
		Assert.assertFalse(p2.getAdresse().isPresent());
		p1.removeAdresse();
		Assert.assertFalse(p1.getAdresse().isPresent());
		
		// test re-assignment of a person
		Address a2 = new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("2").plz("8000").ort("Zürich")
				.build();
		a1.setPerson(p1);
		try {
			a2.setPerson(p1);
			Assert.fail("this call should throw an exception");
		} catch (RuntimeException ex) {
			// expected exception
		}
		Assert.assertEquals(p1, a1.getPerson());
		Assert.assertNull(a2.getPerson());
	}

	/**
	 * Tests of 1:n bi-directional mapping.
	 */
	@Test
	public void testPersonVehicleRelation() {
		Person p1 = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		Car c = new Car("Audi", "Ein Audi");

		// test relation from car
		c.setOwner(p1);
		Assert.assertEquals(1, p1.getVehicles().size());
		Assert.assertEquals("Audi", ((Car) p1.getVehicles().get(0)).getModell());
		Assert.assertEquals(p1, c.getOwner());

		// cancel relation
		c.setOwner(null);
		Assert.assertTrue(p1.getVehicles().isEmpty());
		Assert.assertNull(c.getOwner());

		// test relation from person
		p1.addVehicle(c);
		Assert.assertEquals(1, p1.getVehicles().size());
		Assert.assertEquals("Audi", ((Car) p1.getVehicles().get(0)).getModell());
		Assert.assertEquals(p1, c.getOwner());

		// cancel relation
		p1.removeVehicle(c);
		Assert.assertTrue(p1.getVehicles().isEmpty());
		Assert.assertNull(c.getOwner());

		// test reassignment of a vehicle
		Person p2 = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();

		p1.addVehicle(c);
		Assert.assertEquals(1, p1.getVehicles().size());
		Assert.assertEquals(p1, c.getOwner());

		p2.addVehicle(c);
		Assert.assertEquals(1, p2.getVehicles().size());
		Assert.assertEquals(p2, c.getOwner());
		Assert.assertTrue(p1.getVehicles().isEmpty());

		p1.addVehicle(c);
		Assert.assertEquals(1, p1.getVehicles().size());
		Assert.assertEquals(p1, c.getOwner());
		Assert.assertTrue(p2.getVehicles().isEmpty());

		p1.removeVehicle(c);
		Assert.assertNull(c.getOwner());
		Assert.assertTrue(p1.getVehicles().isEmpty());
		Assert.assertTrue(p2.getVehicles().isEmpty());

		// now we test a vehicle cannot be set twice or removed twice
		p1.addVehicle(c);
		p1.addVehicle(c);
		Assert.assertEquals(1, p1.getVehicles().size());
		p1.removeVehicle(c);
		p1.removeVehicle(c);
		Assert.assertTrue(p1.getVehicles().isEmpty());

		// and in the end we test a list of vehicles cannot be changed outside
		// of the person
		p1.addVehicle(c);
		try {
			p1.getVehicles().add(new Bike(BikeType.Rennrad, "ein neues Rennrad"));
			Assert.fail("this call should throw an exception");
		} catch (UnsupportedOperationException ex) {
			// expected exception
		}
		Assert.assertEquals(1, p1.getVehicles().size());
		try {
			p1.getVehicles().remove(c);
			Assert.fail("this call should throw an exception");
		} catch (UnsupportedOperationException ex) {
			// expected exception
		}
		Assert.assertEquals(1, p1.getVehicles().size());

	}

}
