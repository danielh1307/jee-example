package danielh1307.jee.example.batch;

import java.util.Date;
import java.util.Properties;

import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.BatchStatus;
import javax.ejb.Stateless;

import danielh1307.jee.example.batch.PeriodicOperationService;

@Stateless(mappedName = "PeriodicOperationService")
public class PeriodicOperationServiceImpl implements PeriodicOperationService {

	@Override
	public long start(String jobXmlName, Properties properties) {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		return jobOperator.start(jobXmlName, properties);
	}

	@Override
	public Date getStartTime(long id) {
		return BatchRuntime.getJobOperator().getJobExecution(id).getStartTime();
	}

	@Override
	public Date getEndTime(long id) {
		return BatchRuntime.getJobOperator().getJobExecution(id).getEndTime();
	}

	@Override
	public BatchStatus getBatchStatus(long id) {
		return BatchRuntime.getJobOperator().getJobExecution(id).getBatchStatus();
	}

	@Override
	public long startBatchlet() {
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		long executionId = jobOperator.start("SimpleBatchletJob", new Properties());
		return executionId;
	}

}
