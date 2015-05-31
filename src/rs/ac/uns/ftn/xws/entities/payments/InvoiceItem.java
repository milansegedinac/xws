package rs.ac.uns.ftn.xws.entities.payments;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

import org.hibernate.annotations.Where;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Where(clause = "deleted = 'false'")
@JsonInclude(Include.NON_NULL)
public class InvoiceItem implements Serializable{

	public InvoiceItem() {
		super();
	}

	private static final long serialVersionUID = -1165417510036158730L;

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true, nullable = false)
    protected long id;
	
    protected boolean deleted = false;

    @Version
    protected int version = 0;
	
    protected int orderNumber;
    
    protected String goodsName;

    protected double quantity;
    
    protected String measureUnit;
    
    protected double pricePerUnit;

    protected double amount;
    
    protected double rabatePercentage;
    
    protected double rabateAmount;
    
    protected double minusRabat;
    
    protected double totalTax;
    
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

	public int getOrderNumber() {
		return orderNumber;
	}

	public void setOrderNumber(int orderNumber) {
		this.orderNumber = orderNumber;
	}

	public String getGoodsName() {
		return goodsName;
	}

	public void setGoodsName(String goodsName) {
		this.goodsName = goodsName;
	}

	public double getAmount() {
		return quantity;
	}

	public void setAmount(double amount) {
		this.quantity = amount;
	}

	public String getMeasureUnit() {
		return measureUnit;
	}

	public void setMeasureUnit(String measureUnit) {
		this.measureUnit = measureUnit;
	}

	public double getPricePerUnit() {
		return pricePerUnit;
	}

	public void setPricePerUnit(double pricePerUnit) {
		this.pricePerUnit = pricePerUnit;
	}

	public double getQuantity() {
		return quantity;
	}

	public void setQuantity(double quantity) {
		this.quantity = quantity;
	}

	public double getRabatePercentage() {
		return rabatePercentage;
	}

	public void setRabatePercentage(double rabatePercentage) {
		this.rabatePercentage = rabatePercentage;
	}

	public double getRabateAmount() {
		return rabateAmount;
	}

	public void setRabateAmount(double rabateAmount) {
		this.rabateAmount = rabateAmount;
	}

	public double getMinusRabat() {
		return minusRabat;
	}

	public void setMinusRabat(double minusRabat) {
		this.minusRabat = minusRabat;
	}

	public double getTotalTax() {
		return totalTax;
	}

	public void setTotalTax(double totalTax) {
		this.totalTax = totalTax;
	}
}
