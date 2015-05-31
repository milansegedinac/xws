package rs.ac.uns.ftn.xws.entities.payments;

import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Where(clause = "deleted = 'false'")
@JsonInclude(Include.NON_NULL)
public class Invoice implements Serializable{

	public Invoice() {
		super();
	}

	private static final long serialVersionUID = 2014063575900600644L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;
	
    protected boolean deleted = false;

    @Version
    protected int version = 0;
	
	protected String suplierName;
	
	protected String suplierAddress;

	protected String supplierPib;
	
	protected String buyerName;
	
	protected String buyerAddress;
	
	protected int acountNumber;
	
	protected Date date;
	
	protected double totalGoodsValue;
	
	protected double totalServiceValue;
	
	protected double totalValue;
	
	protected double totalRabate;
	
	protected double totalTax;
	
	protected String currency;
	
	protected double totalAmount;
	
	protected Date currencyDate;
	
    @OneToMany(fetch = FetchType.EAGER, cascade = { CascadeType.ALL })
    @JoinColumn(name="invoiceId")
    @Where(clause = "deleted = 'false'")
    protected Set<InvoiceItem> invoiceItems = new HashSet<InvoiceItem>();

	
	public String getSupplierPib() {
		return supplierPib;
	}

	public void setSupplierPib(String supplierPib) {
		this.supplierPib = supplierPib;
	}

	public String getBuyerName() {
		return buyerName;
	}

	public void setBuyerName(String buyerName) {
		this.buyerName = buyerName;
	}

	public String getBuyerAddress() {
		return buyerAddress;
	}

	public void setBuyerAddress(String buyerAddress) {
		this.buyerAddress = buyerAddress;
	}

	public int getAcountNumber() {
		return acountNumber;
	}

	public void setAcountNumber(int acountNumber) {
		this.acountNumber = acountNumber;
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public int getVersion() {
		return version;
	}

	public void setVersion(int version) {
		this.version = version;
	}

	public String getSuplierName() {
		return suplierName;
	}

	public void setSuplierName(String suplierName) {
		this.suplierName = suplierName;
	}

	public String getSuplierAddress() {
		return suplierAddress;
	}

	public void setSuplierAddress(String suplierAddress) {
		this.suplierAddress = suplierAddress;
	}

	public Set<InvoiceItem> getInvoiceItems() {
		return invoiceItems;
	}

	public void setInvoiceItems(Set<InvoiceItem> invoiceItems) {
		this.invoiceItems = invoiceItems;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public double getTotalGoodsValue() {
		return totalGoodsValue;
	}

	public void setTotalGoodsValue(double totalGoodsValue) {
		this.totalGoodsValue = totalGoodsValue;
	}

	public double getTotalServiceValue() {
		return totalServiceValue;
	}

	public void setTotalServiceValue(double totalServiceValue) {
		this.totalServiceValue = totalServiceValue;
	}

	public double getTotalValue() {
		return totalValue;
	}

	public void setTotalValue(double totalValue) {
		this.totalValue = totalValue;
	}

	public double getTotalRabate() {
		return totalRabate;
	}

	public void setTotalRabate(double totalRabate) {
		this.totalRabate = totalRabate;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}

	public String getCurrency() {
		return currency;
	}

	public void setCurrency(String currency) {
		this.currency = currency;
	}

	public double getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(double totalAmount) {
		this.totalAmount = totalAmount;
	}

	public Date getCurrencyDate() {
		return currencyDate;
	}

	public void setCurrencyDate(Date currencyDate) {
		this.currencyDate = currencyDate;
	}

}
