package br.com.digicom.modelo.repositorio;


import java.util.HashMap;
import java.util.Map;

import br.com.digicom.modelo.AnuncioAds;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.CampanhaAnuncioResultado;
import br.com.digicom.modelo.ModeloCampanhaAds;
import br.com.digicom.modelo.PalavraChaveAds;

import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.callbacks.JsonArrayParser;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.remoting.adapters.RestContractItem;

public class RepositorioBase {

	public static class CampanhaAdRepository extends ModelRepository<CampanhaAds> {
		public CampanhaAdRepository() {
			super("CampanhaAd", CampanhaAds.class);
		}
		public void listaPendente(final ListCallback<CampanhaAds> callback) {
			RestContractItem contrato = new RestContractItem("CampanhaAds/listaParaPublicar","GET");
			this.getRestAdapter().getContract().addItem(contrato, "CampanhaAd.listaParaPublicar");
	        Map<String, Object> params = new HashMap<String, Object>();
	        invokeStaticMethod("listaParaPublicar", params,
	                new JsonArrayParser<CampanhaAds>(this, callback));
	    }
		public void listaParaResultado(final ListCallback<CampanhaAds> callback) {
			RestContractItem contrato = new RestContractItem("CampanhaAds/listaParaResultado","GET");
			this.getRestAdapter().getContract().addItem(contrato, "CampanhaAd.listaParaResultado");
	        Map<String, Object> params = new HashMap<String, Object>();
	        invokeStaticMethod("listaParaResultado", params,
	                new JsonArrayParser<CampanhaAds>(this, callback));
	    }
	}
	
	

	public static class CampanhaAnuncioResultadoRepository extends ModelRepository<CampanhaAnuncioResultado> {
		public CampanhaAnuncioResultadoRepository() {
			super("CampanhaAnuncioResultado", CampanhaAnuncioResultado.class);
		}
		public void listaParaResultadoPorIdCampanha(final Integer idCampanha, final ListCallback<CampanhaAnuncioResultado> callback) {
			RestContractItem contrato = new RestContractItem("CampanhaAds/:id/campanhaAnuncioResultados","GET");
			this.getRestAdapter().getContract().addItem(contrato, "CampanhaAnuncioResultado.listaParaResultadoPorIdCampanha");
	        Map<String, Object> params = new HashMap<String, Object>();
	        params.put("id", idCampanha);
	        params.put("filter", "{ \"include\" : \"anuncioAds\" }");
	        invokeStaticMethod("listaParaResultadoPorIdCampanha", params,
	                new JsonArrayParser<CampanhaAnuncioResultado>(this, callback));
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
