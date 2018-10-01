package br.com.digicom;

import com.google.common.collect.ImmutableMap;
import com.strongloop.android.loopback.Model;
import com.strongloop.android.loopback.ModelRepository;
import com.strongloop.android.remoting.Repository;

import com.strongloop.android.remoting.adapters.Adapter.Callback;
import com.strongloop.android.remoting.adapters.RestAdapter;
import com.strongloop.android.remoting.adapters.RestContractItem;

public class TesteClienteLoopback {

	private static RestAdapter adapter;
	private static Repository testClass;

	public static void main(String[] args) {
		System.out.println("Ola Mundo");
		RestAdapter adapter = new RestAdapter("http://example.com");
		ModelRepository productRepository = adapter.createRepository("product");
		Model pen = productRepository.createModel(ImmutableMap.of("name", "Awesome Pen"));
		//adapter = new RestAdapter("http://validacao.kinghost.net:21101");
		//adapter.invokeStaticMethod("simple.getSecret", null, expectJsonResponse("shhh!"));
		
		getLoopBackAdapter();
	}
	

    public static RestAdapter getLoopBackAdapter() {
        if (adapter == null) {
            // Instantiate the shared RestAdapter. In most circumstances,
            // you'll do this only once; putting that reference in a singleton
            // is recommended for the sake of simplicity.
            // However, some applications will need to talk to more than one
            // server - create as many Adapters as you need.
            adapter = new RestAdapter("http://validacao.kinghost.net:21101/api");
            ModelRepository productRepository = adapter.createRepository("product");
            Model pen = productRepository.createModel(ImmutableMap.of("name", "Awesome Pen"));

            // This boilerplate is required for Lesson Three.
            adapter.getContract().addItem(new RestContractItem("locations/nearby", "GET"),"location.nearby");
        }
        return adapter;
    }

	

}
