package danielh1307.jee.example.lcs;

import java.time.LocalDate;

import org.junit.Assert;
import org.junit.Test;

import danielh1307.jee.example.address.Address;
import danielh1307.jee.example.enums.LifeCycleType;
import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.test.BaseTest;
import danielh1307.jee.example.vehicle.Bike;
import danielh1307.jee.example.vehicle.BikeType;
import danielh1307.jee.example.vehicle.Car;
import danielh1307.jee.example.vehicle.Vehicle;

public class LifeCycleTest extends BaseTest {

	@Test
	public void testPersonLcs() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		Address a = new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("1").plz("8000").ort("Zürich")
				.build();
		p.setAdresse(a);
		
		p = getPersonService().addPerson(p);
		a = getAddressService().getAddress(p.getAdresse().get().getId());
		
		Assert.assertSame(LifeCycleType.AKTIV, p.getLifeCycle());
		Assert.assertSame(LifeCycleType.AKTIV, a.getLifeCycle());
		
		p = getPersonService().inactivatePerson(p.getId());
		
		a = getAddressService().getAddress(p.getAdresse().get().getId());
		
		Assert.assertSame(LifeCycleType.INAKTIV, p.getLifeCycle());
		Assert.assertSame(LifeCycleType.INAKTIV, a.getLifeCycle());
	}
	
	@Test
	public void testVehicleLcs() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1))
				.build();
		Address a = new Address.AddressBuilder().strasse("Bachstrasse").hausnummer("1").plz("8000").ort("Zürich")
				.build();
		p.setAdresse(a);
		p.addVehicle(new Car("Audi", "ein Audi"));
		p.addVehicle(new Bike(BikeType.Cityrad, "ein Cityrad"));
		
		p = getPersonService().addPerson(p);
		
		// check everything is AKTIV
		Assert.assertSame(LifeCycleType.AKTIV, p.getLifeCycle());
		Assert.assertSame(LifeCycleType.AKTIV, p.getAdresse().get().getLifeCycle());
		for (Vehicle v : p.getVehicles()) {
			Assert.assertSame(LifeCycleType.AKTIV, v.getLifeCycle());
		}
		
		int v1Id = p.getVehicles().get(0).getId();
		int v2Id = p.getVehicles().get(1).getId();
		
		// now inactivate just one vehicle
		Vehicle v1 = getVehicleService().inactivateVehicle(v1Id);
		Vehicle v2 = getVehicleService().getVehicle(v2Id);
		
		Assert.assertSame(LifeCycleType.AKTIV, p.getLifeCycle());
		Assert.assertSame(LifeCycleType.AKTIV, p.getAdresse().get().getLifeCycle());
		Assert.assertSame(LifeCycleType.INAKTIV, v1.getLifeCycle());
		Assert.assertSame(LifeCycleType.AKTIV, v2.getLifeCycle());
		
		// inactivate second vehicle - everything must be INAKTIV then
		getVehicleService().inactivateVehicle(v2Id);
		
		p = getPersonService().getPerson(p.getId());
		
		Assert.assertSame(LifeCycleType.INAKTIV, p.getLifeCycle());
		Assert.assertSame(LifeCycleType.INAKTIV, p.getAdresse().get().getLifeCycle());
		for (Vehicle v : p.getVehicles()) {
			Assert.assertSame(LifeCycleType.INAKTIV, v.getLifeCycle());
		}
		
	}

}
