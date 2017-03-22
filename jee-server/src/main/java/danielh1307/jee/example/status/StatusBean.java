package danielh1307.jee.example.status;

import javax.enterprise.context.RequestScoped;
import javax.interceptor.Interceptors;

import danielh1307.jee.example.util.LogMethodInterceptor;

/**
 * Please consider to create an (empty) file beans.xml in META-INF directory to configure CDI.
 * 
 * Beans should be stateless and therefore should be annotated with @RequestScoped. Default is @Dependent, which
 * means their lifecycle is bound to the lifecycle of the object they are injected into. In case of a stateless session bean,
 * this means they keep their state between different calls (if the client communicates with the same stateless bean object).
 *
 */
@RequestScoped
public class StatusBean {
	
	@Interceptors(LogMethodInterceptor.class)
	public String ping(String a) {
		return a;
	}
}
