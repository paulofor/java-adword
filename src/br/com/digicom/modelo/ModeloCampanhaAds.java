package br.com.digicom.modelo;

import com.strongloop.android.loopback.Model;

public class ModeloCampanhaAds extends Model {

	private Integer id;
	private String periodo;
	private Double custoDia;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getPeriodo() {
		return periodo;
	}
	public void setPeriodo(String periodo) {
		this.periodo = periodo;
	}
	public Double getCustoDia() {
		return custoDia;
	}
	public void setCustoDia(Double custoDia) {
		this.custoDia = custoDia;
	}
	
}
