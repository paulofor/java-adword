package br.com.digicom.modelo.repositorio;


import br.com.digicom.modelo.AnuncioAds;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.ModeloCampanhaAds;
import br.com.digicom.modelo.PalavraChaveAds;

import java.util.HashMap;
import java.util.Map;

import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.callbacks.JsonObjectParser;
import com.strongloop.android.loopback.callbacks.ObjectCallback;

public class RepositorioBase {

	public static class CampanhaAdRepository extends ModelRepository<CampanhaAds> {
		public CampanhaAdRepository() {
			super("CampanhaAd", CampanhaAds.class);
		}
		public void listaPendente(final ObjectCallback<CampanhaAds> callback) {
	        Map<String, Object> params = new HashMap<String, Object>();
	        //params.put("id", id);
	        invokeStaticMethod("listaParaPublicar", params,
	                new JsonObjectParser<CampanhaAds>(this, callback));
	    }
		
	}
	
	public static class AnuncioAdRepository extends ModelRepository<AnuncioAds> {
		public AnuncioAdRepository() {
			super("AnuncioAd", AnuncioAds.class);
		}
	}
	
	public static class ModeloCampanhaAdRepository extends ModelRepository<ModeloCampanhaAds> {
		public ModeloCampanhaAdRepository() {
			super("ModeloCampanhaAd", ModeloCampanhaAds.class);
		}
	}
	
	public static class PalavraChaveAdRepository extends ModelRepository<PalavraChaveAds> {
		public PalavraChaveAdRepository() {
			super("PalavraChaveAd", PalavraChaveAds.class);
		}
	}
}
