package rs.ac.uns.ftn.xws.interceptors;

import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.util.Authenticate;
import rs.ac.uns.ftn.xws.util.ServiceException;

@Interceptor
@Authenticate
public class AuthenticationInterceptor {

	public AuthenticationInterceptor() {
		super();
	}

	private static Logger log = Logger.getLogger(AuthenticationInterceptor.class);

	@Context
	private HttpServletRequest request;

	@AroundInvoke
	public Object intercept(InvocationContext context) throws Exception{
		User user = (User) request.getSession().getAttribute("user");
		log.info("user: "+user);
		if (user == null) {
			throw new ServiceException("Not logged in", Status.UNAUTHORIZED);
		}	
		
		Object result = context.proceed();
		return result;
	}

	

}
