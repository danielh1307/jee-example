package danielh1307.jee.example.server.status.service;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.inject.Inject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import danielh1307.jee.example.client.status.StatusService;
import danielh1307.jee.example.server.status.function.StatusBean;
import danielh1307.jee.example.server.status.function.StatusEjb;

/**
 * Please keep in mind a stateless session bean MUST NOT hold any instance variables and thus keep some sort of "state".
 * 
 * The session beans are kept inside an object pool, and although instance variables are possible and keep their state between 
 * two calls, a client can never rely to communicate with the "same" session bean object, so the client must not rely on it
 * keeping its state.
 * 
 * A client should never be asking for or accessing the internal variables of a stateless session bean. So as a rule: Do never
 * use instance variables inside a stateless session bean.
 *
 */
@Stateless(mappedName = "StatusService")
public class StatusServiceImpl implements StatusService {

	private static final Logger logger = LoggerFactory.getLogger(StatusServiceImpl.class);

	@Inject
	private StatusBean statusBean;

	@EJB
	private StatusEjb statusEjb;

	@Override
	public String pingBean(String a) {
		return statusBean.ping(a);
	}
	
	@Override
	public String pingEjb(String a) {
		return statusEjb.ping(a);
	}

	@Override
	public void error() throws Exception {
		try {
			statusEjb.error();
		} catch (Exception e) {
			logger.error("An error occured!", e);
			throw e;
		}
	}

}
