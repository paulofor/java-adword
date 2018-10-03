package br.com.digicom.modelo.repositorio;


import java.util.HashMap;
import java.util.Map;

import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.callbacks.JsonArrayParser;
import com.strongloop.android.loopback.callbacks.JsonObjectParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.remoting.adapters.RestContractItem;

import br.com.digicom.modelo.AnuncioAds;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.ModeloCampanhaAds;
import br.com.digicom.modelo.PalavraChaveAds;

public class RepositorioBase {

	public static class CampanhaAdRepository extends ModelRepository<CampanhaAds> {
		public CampanhaAdRepository() {
			super("CampanhaAd", CampanhaAds.class);
		}
		public void listaPendente(final ListCallback<CampanhaAds> callback) {
			RestContractItem contrato = new RestContractItem("CampanhaAds/listaParaPublicar","GET");
			this.getRestAdapter().getContract().addItem(contrato, "CampanhaAd.listaParaPublicar");
	        Map<String, Object> params = new HashMap<String, Object>();
	        //params.put("id", id);
	        invokeStaticMethod("listaParaPublicar", params,
	                new JsonArrayParser<CampanhaAds>(this, callback));
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
