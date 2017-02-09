package danielh1307.jee.example.client.batch;

import java.util.Date;

import javax.batch.runtime.BatchStatus;
import javax.ejb.Remote;

@Remote
public interface CalcBatchJob {

	public long start();
	
	public long startBatchlet();
	
	public Date getStartTime(long id);
	
	public Date getEndTime(long id);
	
	public BatchStatus getBatchStatus(long id);
	
}
