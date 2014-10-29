package br.furb.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class DetailsFinancialItem {

	private String description; //Descrição
	private Date issueDate; //Data de emissão
	private double grossValue; //Valor bruto
	private double discount; //Desconto
	private double deductions; //Deduções
	private double increase; //Acréscimo
	private double fine; //Multa

	/**
	 * 
	 * @return Descrição
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * 
	 * @param description Descrição
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return Data de emissão
	 */
	public Date getIssueDate() {
		return issueDate;
	}

	/**
	 * 
	 * @param issueDate Data de emissão
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
	 * @return Deduções
	 */
	public double getDeductions() {
		return deductions;
	}

	/**
	 * 
	 * @param deductions Deduções
	 */
	public void setDeductions(double deductions) {
		this.deductions = deductions;
	}

	/**
	 * 
	 * @return Acréscimo
	 */
	public double getIncrease() {
		return increase;
	}

	/**
	 * 
	 * @param increase Acréscimo
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
