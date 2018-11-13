package br.com.digicom.modelo;

import com.strongloop.android.loopback.Model;

public class PalavraChaveRaiz extends Model{

	private String palavra;

	public String getPalavra() {
		return palavra;
	}

	public void setPalavra(String palavra) {
		this.palavra = palavra;
	}
	
	
}
