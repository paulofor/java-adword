package br.com.digicom;

import br.com.digicom.adsservice.CampanhaResultService;
import br.com.digicom.modelo.CampanhaAds;

public class ObtemResultadoCampanha {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		CampanhaAds campanha = new CampanhaAds();
		campanha.setIdAds("1597208602");
		CampanhaResultService srv = new CampanhaResultService();
		srv.atualizaResultado(campanha);
	}

}
