package br.com.digicom;

import br.com.digicom.modelo.AnuncioAds;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.repositorio.RepositorioBase;

import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.loopback.RestAdapter;
import com.strongloop.android.loopback.callbacks.ObjectCallback;
import com.strongloop.android.remoting.Repository;

public class TesteClienteLoopback {

	private static RestAdapter adapter;
	private static Repository testClass;

	public static void main(String[] args) {
		System.out.println("Ola Mundo");
		RestAdapter adapter = new RestAdapter("http://validacao.kinghost.net:21101/api");
		RepositorioBase.AnuncioAdRepository rep = adapter.createRepository(RepositorioBase.AnuncioAdRepository.class);
		rep.findById(4, new ObjectCallback<AnuncioAds>() { 
            @Override 
            public void onSuccess(AnuncioAds model) { 
            	System.out.println("Sucesso: " + model);
            }
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			} 
        }); 

	}


}
