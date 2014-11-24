package br.furb.avamobile.core;

public class FinanceiroData {
	private String dataVencimento;
	private String convenio;
	private float valorBruto;
	private float abatimento;
	private float deducoes;
	private float acrescimos;
	private float multa;
	private String dataPagamento;
	private float valorPago;
	
	
	
	public FinanceiroData(String dataVencimento, String convenio,
			float valorBruto, float abatimento, float deducoes,
			float acrescimos, float multa, String dataPagamento, float valorPago) {
		this.dataVencimento = dataVencimento;
		this.convenio = convenio;
		this.valorBruto = valorBruto;
		this.abatimento = abatimento;
		this.deducoes = deducoes;
		this.acrescimos = acrescimos;
		this.multa = multa;
		this.dataPagamento = dataPagamento;
		this.valorPago = valorPago;
	}
	
	public float getValorPago() {
		return valorPago;
	}
	public void setValorPago(float valorPago) {
		this.valorPago = valorPago;
	}
	public String getDataVencimento() {
		return dataVencimento;
	}
	public void setDataVencimento(String dataVencimento) {
		this.dataVencimento = dataVencimento;
	}
	public String getConvenio() {
		return convenio;
	}
	public void setConvenio(String convenio) {
		this.convenio = convenio;
	}
	public float getValorBruto() {
		return valorBruto;
	}
	public void setValorBruto(float valorBruto) {
		this.valorBruto = valorBruto;
	}
	public float getAbatimento() {
		return abatimento;
	}
	public void setAbatimento(float abatimento) {
		this.abatimento = abatimento;
	}
	public float getDeducoes() {
		return deducoes;
	}
	public void setDeducoes(float deducoes) {
		this.deducoes = deducoes;
	}
	public float getAcrescimos() {
		return acrescimos;
	}
	public void setAcrescimos(float acrescimos) {
		this.acrescimos = acrescimos;
	}
	public float getMulta() {
		return multa;
	}
	public void setMulta(float multa) {
		this.multa = multa;
	}
	public String getDataPagamento() {
		return dataPagamento;
	}
	public void setDataPagamento(String dataPagamento) {
		this.dataPagamento = dataPagamento;
	}
	
	

}
