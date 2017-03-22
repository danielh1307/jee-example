package danielh1307.jee.example.status;

import org.junit.Test;

import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class StatusServiceSingletonTest extends BaseTest {
	
	@Test
	public void testSingleton() throws Exception {		
		Assert.assertEquals("Ready", getStatusServiceSingleton(false).getState());
		Assert.assertEquals(1, getStatusServiceSingleton(false).getMethodCallCount());
		
		// the singleton keeps its state even between different lookups
		Assert.assertEquals("Ready", getStatusServiceSingleton(true).getState());
		Assert.assertEquals(2, getStatusServiceSingleton(false).getMethodCallCount());
		
		// reset the state of the bean
		getStatusServiceSingleton(false).reset();
	}

}
