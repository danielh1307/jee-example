package danielh1307.jee.example.status;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class StatusEjb {

	public String ping(String a) {
		return a;
	}

	public void error() {
		throw new RuntimeException("Error handling is working");
	}
}
