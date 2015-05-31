package rs.ac.uns.ftn.xws.util;

import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

public class ServiceException extends WebApplicationException {
	private static final long serialVersionUID = 1L;
	
	private Object entity;
    private Status status;
    
    public ServiceException(Object entity, Status status) {
        super();
        this.entity = entity;
        this.status = status;
    }
    
    @Override
    public Response getResponse() {
        return Response.status(status).entity(entity).build();
    }
}
