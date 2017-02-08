package danielh1307.jee.example.client.status;

import org.junit.Test;

import danielh1307.jee.example.client.status.StatusServiceStateful;
import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class StatusServiceStatefulTest extends BaseTest {

	@Test
	public void testStatefulBean() throws Exception {
		String state = "state";
		
		StatusServiceStateful statefulService = getService(StatusServiceStateful.class);
		
		Assert.assertEquals(0, statefulService.getMethodCallCount());
		Assert.assertNull(statefulService.getValue());
				
		statefulService.setValue(state);		
		Assert.assertEquals(state, statefulService.getValue());
		Assert.assertEquals(3, statefulService.getMethodCallCount());	
		
		// after a new lookup, the state is lost
		statefulService = getService(StatusServiceStateful.class);
		Assert.assertEquals(0, statefulService.getMethodCallCount());
		Assert.assertNull(statefulService.getValue());
	}
	
}
