package br.com.digicom.modelo;

import com.strongloop.android.loopback.Model;

public class CampanhaAds extends Model{
	
	
	private String nome;
	private Integer id;
	private String idAds;
	private String dataFinal;
	private String dataInicial;
	private Integer quantidadeImpressao;
	private Integer quantidadeClique;
	private Integer quantidadeConversao;
	private Double orcamentoDia;
	private Double orcamentoTotalPlanejado;
	private Double orcamentoTotalExecutado;
	private Integer paginaValidacaoWebId;
	private Integer modeloCampanhaId;
	
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getIdAds() {
		return idAds;
	}

	public void setIdAds(String idAds) {
		this.idAds = idAds;
	}

	public String getDataFinal() {
		return dataFinal;
	}

	public void setDataFinal(String dataFinal) {
		this.dataFinal = dataFinal;
	}

	public String getDataInicial() {
		return dataInicial;
	}

	public void setDataInicial(String dataInicial) {
		this.dataInicial = dataInicial;
	}

	public Integer getQuantidadeImpressao() {
		return quantidadeImpressao;
	}

	public void setQuantidadeImpressao(Integer quantidadeImpressao) {
		this.quantidadeImpressao = quantidadeImpressao;
	}

	public Integer getQuantidadeClique() {
		return quantidadeClique;
	}

	public void setQuantidadeClique(Integer quantidadeClique) {
		this.quantidadeClique = quantidadeClique;
	}

	public Integer getQuantidadeConversao() {
		return quantidadeConversao;
	}

	public void setQuantidadeConversao(Integer quantidadeConversao) {
		this.quantidadeConversao = quantidadeConversao;
	}

	public Double getOrcamentoDia() {
		return orcamentoDia;
	}

	public void setOrcamentoDia(Double orcamentoDia) {
		this.orcamentoDia = orcamentoDia;
	}

	public Double getOrcamentoTotalPlanejado() {
		return orcamentoTotalPlanejado;
	}

	public void setOrcamentoTotalPlanejado(Double orcamentoTotalPlanejado) {
		this.orcamentoTotalPlanejado = orcamentoTotalPlanejado;
	}

	public Double getOrcamentoTotalExecutado() {
		return orcamentoTotalExecutado;
	}

	public void setOrcamentoTotalExecutado(Double orcamentoTotalExecutado) {
		this.orcamentoTotalExecutado = orcamentoTotalExecutado;
	}

	public Integer getPaginaValidacaoWebId() {
		return paginaValidacaoWebId;
	}

	public void setPaginaValidacaoWebId(Integer paginaValidacaoWebId) {
		this.paginaValidacaoWebId = paginaValidacaoWebId;
	}

	public Integer getModeloCampanhaId() {
		return modeloCampanhaId;
	}

	public void setModeloCampanhaId(Integer modeloCampanhaId) {
		this.modeloCampanhaId = modeloCampanhaId;
	}

	

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	

}
