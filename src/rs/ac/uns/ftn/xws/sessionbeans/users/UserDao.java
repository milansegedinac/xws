package rs.ac.uns.ftn.xws.sessionbeans.users;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.persistence.Query;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.core.Context;

import org.apache.log4j.Logger;




import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(UserDaoLocal.class)
public class UserDao extends GenericDaoBean<User, Long> implements UserDaoLocal{

	@Context
	private HttpServletRequest request;

	private static Logger log = Logger.getLogger(UserDao.class);
	
	@Override
	public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException{
		//log.info("username: "+username);
		//log.info("password: "+password);
		MessageDigest md = MessageDigest.getInstance("MD5");
		md.update(password.getBytes("UTF-8"));
		byte byteData[] = md.digest();
		StringBuffer sb = new StringBuffer();
		for (int i = 0; i < byteData.length; i++) {
			sb.append(Integer.toString((byteData[i] & 0xff) + 0x100, 16).substring(1));
		}
		password = sb.toString();
		log.info("password: "+password);
		
		
		Query q = em.createQuery("select distinct u from " +
				"User u where u.username = :username " + 
				"and u.password = :password");
		q.setParameter("username", username);
		q.setParameter("password", password);
		@SuppressWarnings("unchecked")
		List<User> users = q.getResultList();
		if (users.size() == 1){
			request.getSession().setAttribute("user", users.get(0));
			return users.get(0);
		}
		else
			return null;
	}
	
	@Override
	public void logout(){
		//log.info("LOGOUT");
		request.getSession().invalidate();
		
	}
}
