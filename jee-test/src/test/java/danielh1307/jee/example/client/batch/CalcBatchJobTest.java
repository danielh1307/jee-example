package danielh1307.jee.example.client.batch;

import org.junit.Test;

import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class CalcBatchJobTest extends BaseTest {
	
	@Test
	public void testCalcBatchJob() throws Exception {
		CalcBatchJob calcBatchJob = getService(CalcBatchJob.class);
		
		long id = calcBatchJob.start();
		
		Assert.assertTrue(id > 0);
		Assert.assertNotNull(calcBatchJob.getStartTime(id));
		Thread.sleep(2000); // FIXME: better solution: check job status
		Assert.assertNotNull(calcBatchJob.getEndTime(id));
	}

}
