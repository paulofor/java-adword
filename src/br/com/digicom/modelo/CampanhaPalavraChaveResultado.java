package br.com.digicom.modelo;

import java.util.HashMap;
import java.util.Map;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.BeanUtil;

public class CampanhaPalavraChaveResultado extends Model {

	private String idAds;
	private Integer quantidadeImpressao;
	private Integer quantidadeClique;
	private Double custo;
	private Integer palavraChaveAdsId;
	private Integer campanhaAdsId;
	
	private PalavraChaveAds palavraChaveAds;
	
	
	public void setId(Integer id) {
		this.setIdObjeto(id);
	}
	
	public Map<String, ? extends Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idAds", idAds);
		map.put("quantidadeImpressao", (quantidadeImpressao!=null?quantidadeImpressao:0));
		map.put("quantidadeClique", (quantidadeClique!=null?quantidadeClique:0));
		map.put("custo", (custo!=null?custo:0));
		map.put("id", this.getId());
		map.put("anuncioAdsId", this.getPalavraChaveAdsId());
		map.put("campanhaAdsId", this.getCampanhaAdsId());
		return map;
	}
	
	
	public String getIdAds() {
		return idAds;
	}
	public void setIdAds(String idAds) {
		this.idAds = idAds;
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
	public Double getCusto() {
		return custo;
	}
	public void setCusto(Double custo) {
		this.custo = custo;
	}
	public Integer getPalavraChaveAdsId() {
		return palavraChaveAdsId;
	}
	public void setPalavraChaveAdsId(Integer palavraChaveAdsId) {
		this.palavraChaveAdsId = palavraChaveAdsId;
	}
	public Integer getCampanhaAdsId() {
		return campanhaAdsId;
	}
	public void setCampanhaAdsId(Integer campanhaAdsId) {
		this.campanhaAdsId = campanhaAdsId;
	}
	
	public PalavraChaveAds getPalavraChaveAds() {
		return this.palavraChaveAds;
	}

	public void setPalavraChaveAds(Object palavraChaveAds) {
		this.palavraChaveAds = new PalavraChaveAds();
		BeanUtil.setProperties(this.palavraChaveAds, (Map<String, ? extends Object>) palavraChaveAds, true);

	}
}
