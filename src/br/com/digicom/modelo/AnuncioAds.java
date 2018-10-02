package br.com.digicom.modelo;

import com.strongloop.android.loopback.Model;

public class AnuncioAds extends Model {

	private Integer id;
	private Integer idAds;
	private Integer projetoCanvasMySqlId;

	private String titulo1;
	private String titulo2;
	private String titulo3;
	private String descricao1;
	private String descricao2;

	public String getTitulo1() {
		return titulo1;
	}

	public void setTitulo1(String titulo1) {
		this.titulo1 = titulo1;
	}

	public String getTitulo2() {
		return titulo2;
	}

	public void setTitulo2(String titulo2) {
		this.titulo2 = titulo2;
	}

	public String getTitulo3() {
		return titulo3;
	}

	public void setTitulo3(String titulo3) {
		this.titulo3 = titulo3;
	}

	public String getDescricao1() {
		return descricao1;
	}

	public void setDescricao1(String descricao1) {
		this.descricao1 = descricao1;
	}

	public String getDescricao2() {
		return descricao2;
	}

	public void setDescricao2(String descricao2) {
		this.descricao2 = descricao2;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getIdAds() {
		return idAds;
	}

	public void setIdAds(Integer idAds) {
		this.idAds = idAds;
	}

	public Integer getProjetoCanvasMySqlId() {
		return projetoCanvasMySqlId;
	}

	public void setProjetoCanvasMySqlId(Integer projetoCanvasMySqlId) {
		this.projetoCanvasMySqlId = projetoCanvasMySqlId;
	}
	
	public String toString() {
		return "titulo1 = " + titulo1 + ", titulo2 = " + titulo2 + ", titulo3 = " + titulo3;
	}

}
