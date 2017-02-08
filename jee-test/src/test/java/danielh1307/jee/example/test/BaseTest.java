package danielh1307.jee.example.test;

import java.util.Properties;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import org.junit.BeforeClass;

public class BaseTest {

	protected static InitialContext ic;

	@BeforeClass
	public static void before() throws NamingException {
		Properties environment = new Properties();
		environment.put(Context.INITIAL_CONTEXT_FACTORY, "weblogic.jndi.WLInitialContextFactory");
		environment.put(Context.PROVIDER_URL, "t3://localhost:7001");
		environment.put(Context.SECURITY_PRINCIPAL, "weblogic");
		environment.put(Context.SECURITY_CREDENTIALS, "appl_123");

		ic = new InitialContext(environment);
	}
	
	@SuppressWarnings("unchecked")
	protected <T> T getService(Class<T> service) throws NamingException {
		return (T) ic.lookup(service.getSimpleName() + "#" + service.getName());
	}

}
