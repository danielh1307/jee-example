package danielh1307.jee.example.status;

import javax.ejb.Remote;

@Remote
public interface StatusService {
	
	public String pingBean(String a);
	
	public String pingEjb(String a);
	
	public void demonstrateHibernate(int personId);
	
	public void error() throws Exception;

}
