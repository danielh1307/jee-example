package danielh1307.jee.example.util;

import java.util.UUID;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

import org.slf4j.MDC;

public class MDCInterceptor {
	
	public static final String SESSION_ID = "sessionId";
	
	@AroundInvoke
	public Object addMdc(InvocationContext ctx) throws Exception {
		MDC.put(SESSION_ID, UUID.randomUUID().toString());
		return ctx.proceed();
	}

}
