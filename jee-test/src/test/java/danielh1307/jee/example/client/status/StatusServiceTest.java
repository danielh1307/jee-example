package danielh1307.jee.example.client.status;

import javax.ejb.EJBException;

import org.junit.Test;

import danielh1307.jee.example.client.status.StatusService;
import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class StatusServiceTest extends BaseTest {

	private static final String PING = "ping";

	@Test
	public void testPingBean() throws Exception {
		StatusService statusService = getService(StatusService.class);
		Assert.assertEquals(PING, statusService.pingBean(PING));
	}
	
	@Test
	public void testPingEjb() throws Exception {
		StatusService statusService = getService(StatusService.class);
		Assert.assertEquals(PING, statusService.pingEjb(PING));
	}

	@Test
	public void testError() throws Exception {
		StatusService statusService = getService(StatusService.class);
		try {
			statusService.error();
			Assert.fail("Exception should be thrown");
		} catch (EJBException ex) {
			// expected exception
		} catch (Exception ex) {
			throw ex;
		}
	}
}
