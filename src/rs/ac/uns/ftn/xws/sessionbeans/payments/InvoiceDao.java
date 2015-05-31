package rs.ac.uns.ftn.xws.sessionbeans.payments;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.entities.payments.Invoice;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(InvoiceDaoLocal.class)
public class InvoiceDao extends GenericDaoBean<Invoice, Long> implements InvoiceDaoLocal{

}
