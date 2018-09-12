package br.com.digicom;

import br.com.digicom.adsservice.CampanhaAdsService;
import br.com.digicom.modelo.CampanhaAds;

public class CriadorAnuncio {

	
	
	public static void main(String[] args) {
		CampanhaAds campanha = new CampanhaAds();
		campanha.setNome("Primeira");
		CampanhaAdsService servico = new CampanhaAdsService();
		servico.cria(campanha);
	}
}
