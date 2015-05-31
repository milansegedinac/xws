package rs.ac.uns.ftn.xws.sessionbeans.users;

import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;

import rs.ac.uns.ftn.xws.entities.users.User;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDao;

public interface UserDaoLocal extends GenericDao<User, Long>{

	public User login(String username, String password) throws UnsupportedEncodingException, NoSuchAlgorithmException;

	public void logout();

}
