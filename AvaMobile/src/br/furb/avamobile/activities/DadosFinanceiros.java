package br.furb.avamobile.activities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class DadosFinanceiros  implements Serializable {
	private List<String> Dados;
	private String texto;
	private int iconeRID;
	
	public DadosFinanceiros (String texto, int icone, ArrayList<String> dados2) {
        this.texto = texto;
        this.Dados = dados2;
        this.iconeRID = icone;
    }
	
	public List<String> getDados() {
        return Dados;
    }

	public String getName() {
		return texto;
	}

	public int getIconeRID() {
		return iconeRID;
	}

	public void setIconeRID(int iconeRID) {
		this.iconeRID = iconeRID;
	}
}
