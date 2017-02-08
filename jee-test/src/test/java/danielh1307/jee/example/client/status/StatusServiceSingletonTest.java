package danielh1307.jee.example.client.status;

import org.junit.Test;

import danielh1307.jee.example.client.status.StatusServiceSingleton;
import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class StatusServiceSingletonTest extends BaseTest {
	
	@Test
	public void testSingleton() throws Exception {
		StatusServiceSingleton singletonService = getService(StatusServiceSingleton.class);
		
		Assert.assertEquals("Ready", singletonService.getState());
		Assert.assertEquals(1, singletonService.getMethodCallCount());
		
		// the singleton keeps its state even between different lookups
		singletonService = getService(StatusServiceSingleton.class);
		Assert.assertEquals("Ready", singletonService.getState());
		Assert.assertEquals(2, singletonService.getMethodCallCount());
		
		// reset the state of the bean
		singletonService.reset();
	}

}
