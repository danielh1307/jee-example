package danielh1307.jee.example.status;

import javax.ejb.Remote;

@Remote
public interface StatusServiceStateful {
	
	public void setValue(String value);
	
	public String getValue();
	
	public int getMethodCallCount();

}
