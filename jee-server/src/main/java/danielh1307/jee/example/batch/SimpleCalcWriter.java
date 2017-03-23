package danielh1307.jee.example.batch;

import java.util.List;

import javax.batch.api.chunk.AbstractItemWriter;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * This is the writer of the process SimpleCalculationJob.
 *
 */
@Named
public class SimpleCalcWriter extends AbstractItemWriter {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleCalcWriter.class);

	/**
	 * This method is called after [chunk item-count] items are read. All the collected
	 * items are given to this method.
	 */
	@Override
	public void writeItems(List<Object> arg0) throws Exception {
		logger.info("Call to writeItem()");
		for (Object o : arg0) {
			logger.info("Result: " + o.toString());
		}
	}

}
