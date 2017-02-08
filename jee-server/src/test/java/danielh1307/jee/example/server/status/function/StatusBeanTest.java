package danielh1307.jee.example.server.status.function;

import org.junit.Assert;
import org.junit.Test;

import danielh1307.jee.example.server.status.function.StatusBean;

/**
 * 
 * Simple unit tests for {@link StatusBean}.
 *
 */
public class StatusBeanTest {
	
	@Test
	public void testPing() {
		StatusBean s = new StatusBean();
		Assert.assertEquals("Hallo", s.ping("Hallo"));
	}

}
