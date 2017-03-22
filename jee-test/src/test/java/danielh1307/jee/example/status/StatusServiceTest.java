package danielh1307.jee.example.status;

import java.time.LocalDate;

import javax.ejb.EJBException;

import org.junit.Test;

import danielh1307.jee.example.person.Person;
import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class StatusServiceTest extends BaseTest {

	private static final String PING = "ping";

	@Test
	public void testPingBean() throws Exception {
		Assert.assertEquals(PING, getStatusService().pingBean(PING));
	}
	
	@Test
	public void testPingEjb() throws Exception {
		Assert.assertEquals(PING, getStatusService().pingEjb(PING));
	}

	@Test
	public void testError() throws Exception {
		try {
			getStatusService().error();
			Assert.fail("Exception should be thrown");
		} catch (EJBException ex) {
			// expected exception
		} catch (Exception ex) {
			throw ex;
		}
	}
	
	@Test
	public void testDemonstrateHibernate() throws Exception {
		Person p = new Person.PersonBuilder().vorname("Max").nachname("Meier").geburtsdatum(LocalDate.of(2000, 1, 1)).build();
		p = getPersonService().addPerson(p);
		
		getStatusService().demonstrateHibernate(p.getId());
	}
}
