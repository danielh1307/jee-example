package danielh1307.jee.example.util;

import java.io.StringWriter;
import java.lang.reflect.Method;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.Logger;
import org.slf4j.MDC;


public class LogMethodInterceptor {
	
	@Inject
	private transient Logger logger;

	@AroundInvoke
	public Object logMethodStart(InvocationContext ctx) throws Exception {
		Method m = ctx.getMethod();
		Object[] parameters = ctx.getParameters();
		StringWriter writer = new StringWriter();
		writer.append("[" + MDC.get(MDCInterceptor.SESSION_ID) + "]: Method " + m.getName() + " of class " + m.getDeclaringClass() + " was called with parameters: ");
		for (Object o : parameters) {
			writer.append("[" + o + "]");
		}
		
		logger.info(writer.toString());		
		return ctx.proceed();
	}
	
}
