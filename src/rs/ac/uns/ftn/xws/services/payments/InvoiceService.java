package rs.ac.uns.ftn.xws.services.payments;



import java.util.List;

import javax.ejb.EJB;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.apache.log4j.Logger;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.sessionbeans.payments.InvoiceDaoLocal;
import rs.ac.uns.ftn.xws.util.Authenticate;

@Path("/invoice")
public class InvoiceService {

	private static Logger log = Logger.getLogger(Invoice.class);

	@EJB
	private InvoiceDaoLocal invoiceDao;

	@GET 
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
	public List<Invoice> findByAll() {
		List<Invoice> retVal = null;
		try {
			retVal = invoiceDao.findAll();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

	@GET 
	@Path("{id}")
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Invoice findById(@PathParam("id") String id) {
		Invoice retVal = null;
		try {
			retVal = invoiceDao.findById(Long.parseLong(id));
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Invoice create(Invoice entity) {
		log.info("POST");
    	Invoice retVal = null;
		try {
			System.out.println("entity: "+entity);
			retVal = invoiceDao.persist(entity);
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }
    
    @PUT
    @Path("{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
	@Authenticate
    public Invoice update(Invoice entity) {
    	log.info("PUT");
    	Invoice retVal = null;
        try {
        	retVal = invoiceDao.merge(entity);
        } catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		return retVal;
    }

    @DELETE 
    @Path("{id}")
    @Produces(MediaType.TEXT_HTML)
	@Authenticate
    public String remove(@PathParam("id") Long id) {
    	try {
        	invoiceDao.remove(id);
        } catch (Exception e) {
        	log.error(e.getMessage(), e);
        }
    	return "ok";
    }
    

}
