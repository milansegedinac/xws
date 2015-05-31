package rs.ac.uns.ftn.xws.services.users;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response.Status;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.sessionbeans.users.UserDaoLocal;
import rs.ac.uns.ftn.xws.util.ServiceException;

@Path("/user")
public class UserService {

	private static Logger log = Logger.getLogger(UserService.class);

	@EJB
	private UserDaoLocal userDao;

	@GET 
    @Path("login")
    @Produces(MediaType.APPLICATION_JSON)
    public User loginGet(@QueryParam("username") String username, @QueryParam("password") String password) {
    	User user = null;
		try {
        	user = userDao.login(username, password);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
		if(user==null){
			throw new ServiceException("Wrong username or password", Status.FORBIDDEN);
		}
		log.info("USER: "+user);
    	return user;
    }
    
	@POST
    @Path("login")
    @Produces(MediaType.TEXT_HTML)
    @Consumes(MediaType.APPLICATION_JSON)
    public String login(User sentUser) {
    	User user = null;
		try {
        	user = userDao.login(sentUser.getUsername(), sentUser.getPassword());
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
		if(user==null){//ako ne uspe prijava posalje se greska 403 - znam sta hoces, ali ti ne dozvoljavam 
			throw new ServiceException("Wrong username or password", Status.FORBIDDEN);
		}
    	return "ok";
    }
	
	@GET
    @Path("logout")
    @Produces(MediaType.TEXT_HTML)
    public String logout() {
		try {
			userDao.logout();
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	return "ok";
    }

}
