package rs.ac.uns.ftn.xws.sessionbeans.payments;

import javax.ejb.Local;
import javax.ejb.Stateless;

import rs.ac.uns.ftn.xws.entities.payments.InvoiceItem;
import rs.ac.uns.ftn.xws.sessionbeans.common.GenericDaoBean;

@Stateless
@Local(InvoiceItemDaoLocal.class)
public class InvoiceItemDao extends GenericDaoBean<InvoiceItem, Long> implements InvoiceItemDaoLocal{

}
