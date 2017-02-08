package danielh1307.jee.example.server.status.function;

import javax.ejb.Stateless;

@Stateless
public class StatusEjb {

	public String ping(String a) {
		return a;
	}

	public void error() {
		throw new RuntimeException("Error handling is working");
	}
}
