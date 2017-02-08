package danielh1307.jee.example.server.status.service;

import java.util.concurrent.TimeUnit;

import javax.ejb.Stateful;
import javax.ejb.StatefulTimeout;

import danielh1307.jee.example.client.status.StatusServiceStateful;

/**
 * In a stateful session bean, we are allowed to keep instance variables and thus a (conversational) state.
 * 
 * With every new communication (new lookup), the client gets a new instance of the bean, and is then 
 * communicating only with this instance.
 * 
 * The timeout indicates the amount of time the bean can be idle before it is eligible for removal by the container.
 * It does not mean the bean IS BEING removed, but the client should not rely on it.
 *
 */
@Stateful(mappedName = "StatusServiceStateful")
@StatefulTimeout(unit = TimeUnit.SECONDS, value = 10)
public class StatusServiceStatefulImpl implements StatusServiceStateful {
	
	private String value;
	
	private int count;

	@Override
	public void setValue(String value) {
		count++;
		this.value = value;
	}

	@Override
	public String getValue() {
		count++;
		return value;
	}

	@Override
	public int getMethodCallCount() {
		return count;
	}
}
