package danielh1307.jee.example.server.batch;

import java.util.Date;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.ejb.Stateless;

import danielh1307.jee.example.client.batch.CalcBatchJob;

@Stateless(mappedName = "CalcBatchJob")
public class CalcBatchJobImpl implements CalcBatchJob {

	@Override
	public long start() {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties props = new Properties();
		props.setProperty("calculationInputFileName", "calcInput.txt");
		return jobOperator.start("SimpleCalculationJob", props);
	}

	@Override
	public Date getStartTime(long id) {
		return BatchRuntime.getJobOperator().getJobExecution(id).getStartTime();
	}

	@Override
	public Date getEndTime(long id) {
		return BatchRuntime.getJobOperator().getJobExecution(id).getEndTime();
	}

}
