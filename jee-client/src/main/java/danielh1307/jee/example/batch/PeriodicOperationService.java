package danielh1307.jee.example.batch;

import java.util.Date;
import java.util.Properties;

import javax.batch.runtime.BatchStatus;
import javax.ejb.Remote;

/**
 * 
 * Interface for managing the batch processes.
 *
 */
@Remote
public interface PeriodicOperationService {

	/**
	 * Start a job.
	 * 
	 * @param jobXmlName the name of the job
	 * @param properties the properties of the job
	 * @return the internal id of the job
	 */
	public long start(String jobXmlName, Properties properties);
	
	/**
	 * Start the batchlet.
	 * 
	 * @return the internal id of the job
	 */
	public long startBatchlet();
	
	/**
	 * 
	 * @param id internal id of the job
	 * @return the start time of the job
	 */
	public Date getStartTime(long id);
	
	/**
	 * 
	 * @param id internal id of the job
	 * @return the end time of the job
	 */
	public Date getEndTime(long id);
	
	/**
	 * 
	 * @param id internal id of the job
	 * @return the state of the job
	 */
	public BatchStatus getBatchStatus(long id);
	
}
