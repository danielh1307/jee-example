package danielh1307.jee.example.server.batch;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.util.Properties;

import javax.batch.api.chunk.AbstractItemReader;
import javax.batch.operations.JobOperator;
import javax.batch.runtime.BatchRuntime;
import javax.batch.runtime.context.JobContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Named
public class SimpleCalcReader extends AbstractItemReader {

	private static final Logger logger = LoggerFactory.getLogger(SimpleCalcReader.class);

	@Inject
	private JobContext jobContext;

	private Integer lineNumber = Integer.valueOf(1);
	private String line;
	private BufferedReader br;

	/**
	 * This method is exactly called once at the beginning of the batch. The parameter
	 * checkpoint is null.
	 */
	@Override
	public void open(Serializable checkpoint) throws Exception {
		logger.info("Call to open(), checkpoint is " + checkpoint);
		JobOperator jobOperator = BatchRuntime.getJobOperator();
		Properties jobParameters = jobOperator.getParameters(jobContext.getExecutionId());
		String resourceName = (String) jobParameters.get("calculationInputFileName");
		InputStream inputStream = getClass().getClassLoader().getResourceAsStream(resourceName);
		br = new BufferedReader(new InputStreamReader(inputStream));
	}

	/**
	 * This method is called [chunk item-count] times, then the writer is called with all 
	 * collected items. If the method returns null, the batch jobs (if there are unwritten
	 * items, the item writer is called one last time).
	 */
	@Override
	public Object readItem() throws Exception {
		logger.info("Call to readItem()");

		line = br.readLine();
		if (line == null) {
			// by returning null, we force the job to stop
			return null;
		}

		Integer record = Integer.valueOf(line);
		logger.info("Read value: " + record);
		lineNumber++;
		
		return record;
	}

	/**
	 * This method is called after the items are written. If readItem() returns null and there
	 * are no more items to write, this method is called one last time.
	 */
	@Override
	public Serializable checkpointInfo() throws Exception {
		logger.info("Call to checkpointInfo(), returning " + lineNumber);
		return lineNumber;
	}

	/**
	 * This method is called exactly once at the very end.
	 */
	@Override
	public void close() throws Exception {
		logger.info("Call to close()");
		br.close();
	}
}
