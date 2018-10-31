package br.com.digicom.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.BeanUtil;

public class CampanhaAds extends Model{
	
	
	private List<PalavraChaveAds> palavraChaveAds;
	private List<CampanhaAnuncioResultado> campanhaAnuncioResultados;
	
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
	
	private String listaCampanha ;
	private String urlAlvo;

	
	
	
	public List<CampanhaAnuncioResultado> getCampanhaAnuncioResultados() {
		return campanhaAnuncioResultados;
	}

	public void setCampanhaAnuncioResultados(List<CampanhaAnuncioResultado> campanhaAnuncioResultados) {
		this.campanhaAnuncioResultados = new ArrayList<CampanhaAnuncioResultado>();
		for (int i=0; i<campanhaAnuncioResultados.size(); i++) {
			Object objeto = new CampanhaAnuncioResultado();
			BeanUtil.setProperties(objeto, (Map<String, ? extends Object>) campanhaAnuncioResultados.get(i), true);
			this.campanhaAnuncioResultados.add((CampanhaAnuncioResultado) objeto);
		}
		System.out.println(this + " tam: " + this.campanhaAnuncioResultados.size());
	}

	public String getUrlAlvo() {
		return urlAlvo;
	}

	public void setUrlAlvo(String urlAlvo) {
		this.urlAlvo = urlAlvo;
	}

	public List<PalavraChaveAds> getPalavraChaveAds() {
		return this.palavraChaveAds;
	}

	public void setPalavraChaveAds(List<PalavraChaveAds> palavraChaveAds) {
		this.palavraChaveAds = new ArrayList<PalavraChaveAds>();
		for (int i=0; i<palavraChaveAds.size(); i++) {
			Object objeto = new PalavraChaveAds();
			BeanUtil.setProperties(objeto, (Map<String, ? extends Object>) palavraChaveAds.get(i), true);
			this.palavraChaveAds.add((PalavraChaveAds) objeto);
		}
	}

	
	/*
	public List<CampanhaAnuncioResultado> getAnuncioAds() {
		return anuncioAds;
	}

	public void setAnuncioAds(List<CampanhaAnuncioResultado> anuncioAds) {
		this.anuncioAds = new ArrayList<CampanhaAnuncioResultado>();
		for (int i=0; i<anuncioAds.size(); i++) {
			Object objeto = new AnuncioAds();
			BeanUtil.setProperties(objeto, (Map<String, ? extends Object>) anuncioAds.get(i), true);
			this.anuncioAds.add((CampanhaAnuncioResultado) objeto);
		}
	}
	*/
	

	public String getListaCampanha() {
		return listaCampanha;
	}

	public void setListaCampanha(String listaCampanha) {
		this.listaCampanha = listaCampanha;
	}

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

	
	public String toString() {
		return "nome: " + this.nome + " , id: " + this.id;
	}
	

}
