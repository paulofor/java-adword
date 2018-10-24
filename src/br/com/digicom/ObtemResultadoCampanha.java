package br.com.digicom;

import java.util.List;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;

import br.com.digicom.adsservice.CampanhaResultService;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.repositorio.RepositorioBase;

public class ObtemResultadoCampanha {

	public static void main(String[] args) {
		processa();
	}
	
	private static void processa() {
		System.out.println("Ola Mundo");
		RestAdapter adapter = new RestAdapter("http://validacao.kinghost.net:21101/api");
		RepositorioBase.CampanhaAdRepository rep = adapter.createRepository(RepositorioBase.CampanhaAdRepository.class);
		rep.listaParaResultado(new ListCallback<CampanhaAds>() { 
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onSuccess(List<CampanhaAds> objects) {
				System.out.println("Lista pra resultado contendo " + objects.size() + " itens.");
				for (CampanhaAds item : objects) {
					processaCampanha(item);
				}
			} 
        });
	}
	
	private static void processaCampanha(CampanhaAds campanha) {
		System.out.println("Atualizar campanha " + campanha.getNome());
		CampanhaResultService srv = new CampanhaResultService();
		srv.atualizaResultado(campanha);
		
		IntegracaoMundo facade = new IntegracaoMundo();
		facade.atualizaCampanha(campanha);
	}

}
