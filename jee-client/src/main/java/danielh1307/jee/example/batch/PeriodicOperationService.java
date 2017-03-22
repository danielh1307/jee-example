package danielh1307.jee.example.batch;

import java.util.Date;
import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.ejb.Remote;

@Remote
public interface PeriodicOperationService {

	public long start(String id, Properties properties);
	
	public long restart(long id);
	
	public long startBatchlet();
	
	public Date getStartTime(long id);
	
	public Date getEndTime(long id);
	
	public BatchStatus getBatchStatus(long id);
	
}
