package danielh1307.jee.example.batch;

import javax.batch.api.Batchlet;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleBatchlet implements Batchlet {
	
	private static Logger logger = LoggerFactory.getLogger(SimpleBatchlet.class);

	@Inject
	private JobContext jobContext;

	@Override
	public String process() throws Exception {
		long executionId = jobContext.getExecutionId();
		logger.info("Call to process(), executionId is " + executionId);
		return "SUCCESS";
	}

	@Override
	public void stop() throws Exception {
		logger.info("Call to stop()");
	}

}
