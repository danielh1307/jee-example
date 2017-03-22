package danielh1307.jee.example.status;

import javax.ejb.Remote;

@Remote
public interface StatusServiceSingleton {

	public String getState();
	
	public int getMethodCallCount();
	
	public void reset();
	
}
