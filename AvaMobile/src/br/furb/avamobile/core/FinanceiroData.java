package br.furb.avamobile.core;

import java.util.Date;

public class FinanceiroData
{
	private Date dataVencimento;
	private String convenio;
	private double valorBruto;
	private double abatimento;
	private double deducoes;
	private double acrescimos;
	private double multa;
	private Date dataPagamento;
	private double valorPago;
	
	public FinanceiroData()
	{
		// Construtor vazio
	}

	public FinanceiroData(Date dataVencimento, String convenio,
			double valorBruto, double abatimento, double deducoes,
			double acrescimos, double multa, Date dataPagamento, 
			double valorPago)
	{
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

	public double getValorPago()
	{
		return valorPago;
	}

	public void setValorPago(double valorPago)
	{
		this.valorPago = valorPago;
	}

	public Date getDataVencimento()
	{
		return dataVencimento;
	}

	public void setDataVencimento(Date dataVencimento)
	{
		this.dataVencimento = dataVencimento;
	}

	public String getConvenio()
	{
		return convenio;
	}

	public void setConvenio(String convenio)
	{
		this.convenio = convenio;
	}

	public double getValorBruto()
	{
		return valorBruto;
	}

	public void setValorBruto(double valorBruto)
	{
		this.valorBruto = valorBruto;
	}

	public double getAbatimento()
	{
		return abatimento;
	}

	public void setAbatimento(double abatimento)
	{
		this.abatimento = abatimento;
	}

	public double getDeducoes()
	{
		return deducoes;
	}

	public void setDeducoes(double deducoes)
	{
		this.deducoes = deducoes;
	}

	public double getAcrescimos()
	{
		return acrescimos;
	}

	public void setAcrescimos(double acrescimos)
	{
		this.acrescimos = acrescimos;
	}

	public double getMulta()
	{
		return multa;
	}

	public void setMulta(double multa)
	{
		this.multa = multa;
	}

	public Date getDataPagamento()
	{
		return dataPagamento;
	}

	public void setDataPagamento(Date dataPagamento)
	{
		this.dataPagamento = dataPagamento;
	}

}
