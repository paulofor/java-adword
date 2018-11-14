package br.com.digicom;

import java.util.List;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;

import br.com.digicom.adsservice.PalavraChaveIdeiaService;
import br.com.digicom.modelo.PalavraChaveEstatistica;
import br.com.digicom.modelo.PalavraChaveRaiz;
import br.com.digicom.modelo.repositorio.RepositorioBase;

public class ColetaPalavraChaveApp {

	
	private static RestAdapter adapter =  new RestAdapter("http://validacao.kinghost.net:21101/api");
	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		System.out.println("Inicio Coleta PalavraChave");
		processa();
	}
	
	private static void processa() {
		RepositorioBase.PalavraChaveRaizRepository rep = adapter.createRepository(RepositorioBase.PalavraChaveRaizRepository.class);
		rep.listaParaConsulta(new ListCallback<PalavraChaveRaiz>() { 
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onSuccess(List<PalavraChaveRaiz> objects) {
				System.out.println("Lista pra resultado contendo " + objects.size() + " palavras raiz.");
				for (PalavraChaveRaiz item : objects) {
					processaPalavraChaveRaiz(item);
				}
			} 
        });
	}
	
	private static void processaPalavraChaveRaiz(PalavraChaveRaiz item) {
		System.out.println("Palavra: " + item.getPalavra());
		PalavraChaveIdeiaService servico = new PalavraChaveIdeiaService();
		servico.executaColeta(item);
		registraListaPalavra(servico.getListaResultado());
	}
	
	private static void registraListaPalavra(List<PalavraChaveEstatistica> lista) {
		
	}

}
