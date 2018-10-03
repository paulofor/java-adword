package br.com.digicom;

import java.util.List;

import br.com.digicom.adsservice.CampanhaAdsService;
import br.com.digicom.modelo.CampanhaAds;

public class IntegracaoMundo {

	public void criaCampanha(List<CampanhaAds> objects) {
		CampanhaAdsService servico = new CampanhaAdsService();
		for (CampanhaAds campanha : objects) {
			servico.cria(campanha);
		}
		
	}
}
