package danielh1307.jee.example.test;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;

import danielh1307.jee.example.address.AddressService;
import danielh1307.jee.example.batch.PeriodicOperationService;
import danielh1307.jee.example.person.PersonService;
import danielh1307.jee.example.status.StatusService;
import danielh1307.jee.example.status.StatusServiceSingleton;
import danielh1307.jee.example.status.StatusServiceStateful;
import danielh1307.jee.example.vehicle.VehicleService;

public class BaseTest {

	protected static InitialContext ic;

	Map<String, Object> serviceMap = new HashMap<String, Object>();

	@BeforeClass
	public static void before() throws NamingException {
		Properties environment = new Properties();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		environment.put(Context.PROVIDER_URL, "t3://localhost:7001");
		environment.put(Context.SECURITY_PRINCIPAL, "weblogic");
		environment.put(Context.SECURITY_CREDENTIALS, "appl_123");

		ic = new InitialContext(environment);
	}
	
	protected PersonService getPersonService() throws NamingException {
		return getServiceInstance(PersonService.class);
	}
	
	protected AddressService getAddressService() throws NamingException {
		return getServiceInstance(AddressService.class);
	}
	
	protected VehicleService getVehicleService() throws NamingException {
		return getServiceInstance(VehicleService.class);
	}
	
	protected PeriodicOperationService getPeriodicOperationService() throws NamingException {
		return getServiceInstance(PeriodicOperationService.class);
	}
	
	protected StatusServiceSingleton getStatusServiceSingleton(boolean newInstance) throws NamingException {
		if (newInstance) {
			serviceMap.remove(StatusServiceSingleton.class.getSimpleName());
		}
		return getServiceInstance(StatusServiceSingleton.class);
	}
	
	protected StatusService getStatusService() throws NamingException {
		return getServiceInstance(StatusService.class);
	}
	
	protected StatusServiceStateful getStatusServiceStateful(boolean newInstance) throws NamingException {
		if (newInstance) {
			serviceMap.remove(StatusServiceStateful.class.getName());
		}
		return getServiceInstance(StatusServiceStateful.class);
	}

	@SuppressWarnings("unchecked")
	private <T> T getServiceInstance(Class<T> serviceClass) throws NamingException {
		if (!serviceMap.containsKey(serviceClass)) {
			System.out.println(serviceClass.getSimpleName() + "#" + serviceClass.getName());
			T service = (T) ic.lookup(serviceClass.getSimpleName() + "#" + serviceClass.getName());
			serviceMap.put(serviceClass.getName(), service);
			return service;
		}

		return (T) serviceMap.get(serviceClass.getName());
	}
}
