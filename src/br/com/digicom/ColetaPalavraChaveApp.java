package br.com.digicom;

import java.util.List;

import br.com.digicom.adsservice.PalavraChaveIdeiaService;
import br.com.digicom.modelo.PalavraChaveEstatistica;
import br.com.digicom.modelo.PalavraChaveRaiz;
import br.com.digicom.modelo.repositorio.RepositorioBase;
import br.com.digicom.modelo.util.Util;

import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ListCallback;
import com.strongloop.android.loopback.callbacks.VoidCallback;

public class ColetaPalavraChaveApp {

	
	private static RestAdapter adapter =  new RestAdapter("http://validacao.kinghost.net:21101/api");
	//private static RestAdapter adapter =  new RestAdapter("http://localhost:21101/api");
	
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
					try {
						Thread.sleep(45000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			} 
        });
	}
	
	
	private static void processaPalavraChaveRaiz(PalavraChaveRaiz item) {
		System.out.println("Palavra: " + item.getPalavra());
		PalavraChaveIdeiaService servico = new PalavraChaveIdeiaService();
		servico.executaColeta(item);
		registraListaPalavra(servico.getListaResultado());
		item.setDataUltimaAtualizacao(Util.getDataAtualLoopback());
		item.save(new VoidCallback() {
			@Override
			public void onSuccess() {
				System.out.print("sucesso");
			}
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}

		});
	}
	
	private static void registraListaPalavra(List<PalavraChaveEstatistica> lista) {
		RepositorioBase.PalavraChaveEstatisticaRepository rep = adapter.createRepository(RepositorioBase.PalavraChaveEstatisticaRepository.class);
		
		rep.insereLista(lista, new VoidCallback() { 
			@Override
			public void onError(Throwable t) {
				t.printStackTrace();
			}
			@Override
			public void onSuccess() {
				System.out.println("Inseriu.");
				
			} 
        });
        
	}

}
