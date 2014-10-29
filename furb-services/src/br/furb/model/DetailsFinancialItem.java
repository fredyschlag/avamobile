package br.furb.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailsFinancialItem {

	private String description; //Descri��o
	private Date issueDate; //Data de emiss�o
	private double grossValue; //Valor bruto
	private double discount; //Desconto
	private double deductions; //Dedu��es
	private double increase; //Acr�scimo
	private double fine; //Multa

	/**
	 * 
	 * @return Descri��o
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Descri��o
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return Data de emiss�o
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * 
	 * @param issueDate Data de emiss�o
	 */
	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
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
	 * @return Desconto
	 */
	public double getDiscount() {
		return discount;
	}

	/**
	 * 
	 * @param discount Desconto
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
	

}
