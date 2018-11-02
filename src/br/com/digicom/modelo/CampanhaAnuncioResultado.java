package br.com.digicom.modelo;

import java.util.HashMap;
import java.util.Map;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.remoting.BeanUtil;

public class CampanhaAnuncioResultado extends Model {

	private String idAds;
	private Integer quantidadeImpressao;
	private Integer quantidadeClique;
	private Double custo;
	//private Integer id;
	private Integer anuncioAdsId;
	private Integer campanhaAdsId;

	private AnuncioAds anuncioAds;
	
	
	public Map<String, ? extends Object> toMap() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("idAds", idAds);
		map.put("quantidadeImpressao", (quantidadeImpressao!=null?quantidadeImpressao:0));
		map.put("quantidadeClique", (quantidadeClique!=null?quantidadeClique:0));
		map.put("custo", (custo!=null?custo:0));
		map.put("id", this.getId());
		map.put("anuncioAdsId", this.getAnuncioAdsId());
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

	

	public Integer getAnuncioAdsId() {
		return anuncioAdsId;
	}

	public void setAnuncioAdsId(Integer anuncioAdsId) {
		this.anuncioAdsId = anuncioAdsId;
	}

	public Integer getCampanhaAdsId() {
		return campanhaAdsId;
	}

	public void setCampanhaAdsId(Integer campanhaAdsId) {
		this.campanhaAdsId = campanhaAdsId;
	}

	public AnuncioAds getAnuncioAds() {
		return anuncioAds;
	}

	public void setAnuncioAds(Object anuncioAds) {
		this.anuncioAds = new AnuncioAds();
		BeanUtil.setProperties(this.anuncioAds, (Map<String, ? extends Object>) anuncioAds, true);

	}

	
}
