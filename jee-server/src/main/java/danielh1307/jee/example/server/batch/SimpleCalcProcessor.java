package danielh1307.jee.example.server.batch;

import javax.batch.api.chunk.ItemProcessor;
import javax.inject.Named;

@Named
public class SimpleCalcProcessor implements ItemProcessor {
	
	@Override
	public Object processItem(Object arg0) throws Exception {
		return "I have read this value: [" + arg0 + "]";
	}

}
