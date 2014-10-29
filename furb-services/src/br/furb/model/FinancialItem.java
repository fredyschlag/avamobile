package br.furb.model;

import java.util.Date;
import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class FinancialItem {

	private Date dueDate; //Data de vencimento
	private String convenant; //Conv�nio
	private double grossValue; //Valor bruto
	private double discount; //Abatimento/Desconto
	private double deductions; //Dedu��es
	private double increase; //Acr�scimo
	private double fine; //Multa
	private Date datePayment; //Data de pagamento
	private double amountPaid; //Valor pago
	
	/**
	 * Detalhes
	 * @see DetailsFinancialItem
	 */
	private List<DetailsFinancialItem> details; 

	/**
	 * 
	 * @return Data de vencimento
	 */
	public Date getDueDate() {
		return dueDate;
	}

	/**
	 * 
	 * @param dueDate Data de vencimento
	 */
	public void setDueDate(Date dueDate) {
		this.dueDate = dueDate;
	}

	/**
	 * 
	 * @return Conv�nio
	 */
	public String getConvenant() {
		return convenant;
	}

	/**
	 * 
	 * @param convenant Conv�nio
	 */
	public void setConvenant(String convenant) {
		this.convenant = convenant;
	}

	/**
	 * 
	 * @return Valor bruto
	 */
	public double getGrossValue() {
		return grossValue;
	}

	/**
	 * 
	 * @param grossValue Valor bruto
	 */
	public void setGrossValue(double grossValue) {
		this.grossValue = grossValue;
	}

	/**
	 * 
	 * @return Abatimento/Desconto
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * 
	 * @param discount Abatimento/Desconto
	 */
	public void setDiscount(double discount) {
		this.discount = discount;
	}

	/**
	 * 
	 * @return Dedu��es
	 */
	public double getDeductions() {
		return deductions;
	}

	/**
	 * 
	 * @param deductions Dedu��es
	 */
	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	/**
	 * 
	 * @return Acr�scimo
	 */
	public double getIncrease() {
		return increase;
	}

	/**
	 * 
	 * @param increase Acr�scimo
	 */
	public void setIncrease(double increase) {
		this.increase = increase;
	}

	/**
	 * 
	 * @return Multa
	 */
	public double getFine() {
		return fine;
	}

	/**
	 * 
	 * @param fine Multa
	 */
	public void setFine(double fine) {
		this.fine = fine;
	}

	/**
	 * 
	 * @return Data do pagamento
	 */
	public Date getDatePayment() {
		return datePayment;
	}

	/**
	 * 
	 * @param datePayment Data do pagamento
	 */
	public void setDatePayment(Date datePayment) {
		this.datePayment = datePayment;
	}

	/**
	 * 
	 * @return Valor pago
	 */
	public double getAmountPaid() {
		return amountPaid;
	}

	/**
	 * 
	 * @param amountPaid Valor pago
	 */
	public void setAmountPaid(double amountPaid) {
		this.amountPaid = amountPaid;
	}

	/**
	 * 
	 * @return Detalhes {@link DetailsFinancialItem}
	 */
	public List<DetailsFinancialItem> getDetails() {
		return details;
	}

	/**
	 * 
	 * @param details Detalhes {@link DetailsFinancialItem}
	 */
	public void setDetails(List<DetailsFinancialItem> details) {
		this.details = details;
	}

}
