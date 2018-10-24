package br.com.digicom;

import java.util.List;

import com.strongloop.android.loopback.callbacks.VoidCallback;

import br.com.digicom.adsservice.CampanhaAdsService;
import br.com.digicom.modelo.CampanhaAds;

public class IntegracaoMundo {

	public void criaCampanha(List<CampanhaAds> objects) {
		CampanhaAdsService servico = new CampanhaAdsService();
		for (CampanhaAds campanha : objects) {
			servico.cria(campanha);
			System.out.println("IdAds: " + campanha.getIdAds());
			campanha.save(new VoidCallback() {

				@Override
				public void onSuccess() {
					// TODO Auto-generated method stub
					System.out.print("sucesso");
				}

				@Override
				public void onError(Throwable t) {
					// TODO Auto-generated method stub
					t.printStackTrace();
				}

			});
		}
	}

	public void atualizaCampanha(CampanhaAds item) {
		CampanhaAdsService servico = new CampanhaAdsService();
		item.save(new VoidCallback() {
			@Override
			public void onSuccess() {
				// TODO Auto-generated method stub
				System.out.print("sucesso");
			}
			@Override
			public void onError(Throwable t) {
				// TODO Auto-generated method stub
				t.printStackTrace();
			}

		});

	}

}
