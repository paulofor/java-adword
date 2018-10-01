package br.com.digicom.modelo.repositorio;


import br.com.digicom.modelo.AnuncioAds;
import br.com.digicom.modelo.CampanhaAds;

import com.strongloop.android.loopback.ModelRepository;

public class RepositorioBase {

	public static class CampanhaAdRepository extends ModelRepository<CampanhaAds> {

		public CampanhaAdRepository() {
			super("CampanhaAd", CampanhaAds.class);
		}
	}
	
	public static class AnuncioAdRepository extends ModelRepository<AnuncioAds> {

		public AnuncioAdRepository() {
			super("AnuncioAd", AnuncioAds.class);
		}
	}
}
