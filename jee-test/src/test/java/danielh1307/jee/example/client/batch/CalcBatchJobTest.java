package danielh1307.jee.example.client.batch;

import javax.batch.runtime.BatchStatus;

import org.junit.Before;
import org.junit.Test;

import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class CalcBatchJobTest extends BaseTest {
	
	private CalcBatchJob calcBatchJob;
	
	@Before
	public void init() throws Exception {
		calcBatchJob = getService(CalcBatchJob.class);
	}
	
	@Test
	public void testCalcBatchJob() throws Exception {
		testJob(calcBatchJob.start());
	}
	
	@Test
	public void testBatchlet() throws Exception {		
		testJob(calcBatchJob.startBatchlet());
	}

	private void testJob(long id) {
		Assert.assertTrue(id > 0);
		Assert.assertNotNull(calcBatchJob.getStartTime(id));
		
		BatchStatus status = null;
		while (status != BatchStatus.COMPLETED) {
			status = calcBatchJob.getBatchStatus(id);
			if (status != BatchStatus.STARTED && status != BatchStatus.COMPLETED) {
				Assert.fail("Invalid status: " + status);
			}
		}
		Assert.assertNotNull(calcBatchJob.getEndTime(id));
	}
}
