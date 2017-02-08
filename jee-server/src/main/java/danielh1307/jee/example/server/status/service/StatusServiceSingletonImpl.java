package danielh1307.jee.example.server.status.service;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;

import danielh1307.jee.example.client.status.StatusServiceSingleton;

/**
 * As the name indicates, there is only one instance of that bean, and it keeps its state as long as the 
 * application server is running. The @Startup annotation marks this bean for eager initialization during
 * application startup sequence.
 *
 */
@Startup
@Singleton(mappedName = "StatusServiceSingleton")
public class StatusServiceSingletonImpl implements StatusServiceSingleton {
	
	private String state;
	private int count;
	
	@PostConstruct
	public void init() {
		state = "Ready";
	}

	@Override
	public String getState() {
		count++;
		return state;
	}

	@Override
	public int getMethodCallCount() {
		return count;
	}

	@Override
	public void reset() {
		count = 0;
	}
}
