package danielh1307.jee.example.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 
 * This is the processor of the process SimpleCalculationJob.
 *
 */
@Named
public class SimpleCalcProcessor implements ItemProcessor {
	
	private static final Logger logger = LoggerFactory.getLogger(SimpleCalcProcessor.class);
	
	@Override
	public Object processItem(Object arg0) throws Exception {
		logger.info("I am processing this value: " + arg0);
		return "I have read this value: [" + arg0 + "]";
	}

}
