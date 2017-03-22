package danielh1307.jee.example.batch;

import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.naming.NamingException;

import org.junit.Test;

import danielh1307.jee.example.test.BaseTest;
import junit.framework.Assert;

public class CalcBatchJobTest extends BaseTest {
	
	@Test
	public void testCalcBatchJob() throws Exception {
		Properties props = new Properties();
		props.setProperty("calculationInputFileName", "calcInput.txt");
		testJob(getPeriodicOperationService().start("SimpleCalculationJob", props));
	}
	
	@Test
	public void testBatchlet() throws Exception {		
		testJob(getPeriodicOperationService().startBatchlet());
	}

	private void testJob(long id) throws NamingException {
		Assert.assertTrue(id > 0);
		Assert.assertNotNull(getPeriodicOperationService().getStartTime(id));
		
		BatchStatus status = null;
		while (status != BatchStatus.COMPLETED) {
			status = getPeriodicOperationService().getBatchStatus(id);
			if (status != BatchStatus.STARTED && status != BatchStatus.COMPLETED) {
				Assert.fail("Invalid status: " + status);
			}
		}
		Assert.assertNotNull(getPeriodicOperationService().getEndTime(id));
	}
}
