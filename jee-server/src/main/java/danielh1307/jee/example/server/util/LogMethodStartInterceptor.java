package danielh1307.jee.example.server.util;

import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class LogMethodStartInterceptor {
	
	private Logger logger = LoggerFactory.getLogger(LogMethodStartInterceptor.class);

	@AroundInvoke
	public Object logMethodStart(InvocationContext ctx) throws Exception {
		Method m = ctx.getMethod();
		Object[] parameters = ctx.getParameters();
		StringWriter writer = new StringWriter();
		writer.append("Method " + m.getName() + " of class " + m.getDeclaringClass() + " was called with parameters: ");
		for (Object o : parameters) {
			writer.append("[" + o + "]");
		}
		
		logger.info(writer.toString());		
		return ctx.proceed();
	}
	
}
