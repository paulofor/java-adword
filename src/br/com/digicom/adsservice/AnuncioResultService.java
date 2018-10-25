package br.com.digicom.adsservice;

import static java.nio.charset.StandardCharsets.UTF_8;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.rmi.RemoteException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.SystemUtils;

import com.google.api.ads.adwords.axis.v201802.cm.ApiException;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.client.reporting.ReportingConfiguration;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;
import com.google.api.ads.adwords.lib.jaxb.v201802.DownloadFormat;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponse;
import com.google.api.ads.adwords.lib.utils.ReportDownloadResponseException;
import com.google.api.ads.adwords.lib.utils.ReportException;
import com.google.api.ads.adwords.lib.utils.v201802.ReportDownloaderInterface;
import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Maps;
import com.google.common.primitives.Longs;

import br.com.digicom.AdsService;
import br.com.digicom.modelo.CampanhaAds;

public class AnuncioResultService extends AdsService {
	
	private CampanhaAds campanha = null;

	@Override
	protected void runExample(AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws RemoteException, ApiException {


		ReportingConfiguration reportingConfiguration = new ReportingConfiguration.Builder()
				.skipReportHeader(true).skipColumnHeader(true).skipReportSummary(true)
				.includeZeroImpressions(false).build();
		session.setReportingConfiguration(reportingConfiguration);

		ReportDownloaderInterface reportDownloader = adWordsServices.getUtility(session,ReportDownloaderInterface.class);

		
	    String query = "Select Impressions , Clicks, Cost "
	    		+ "FROM AD_PERFORMANCE_REPORT  where Id = ";
	    		
		BufferedReader reader = null;
		try {
			final ReportDownloadResponse response = reportDownloader.downloadReport(query, DownloadFormat.CSV);
			reader = new BufferedReader(new InputStreamReader(response.getInputStream(), UTF_8));
			Map<String, Long> impressionsByAdNetworkType1 = Maps.newTreeMap();
			String line;
			Splitter splitter = Splitter.on(',');
			while ((line = reader.readLine()) != null) {
				System.out.println(line);
				List<String> values = splitter.splitToList(line);
				//Integer impressao = Integer.parseInt(values.get(0));
				//Integer click = Integer.parseInt(values.get(1));
				//Double custo = Double.parseDouble(values.get(2));
				//custo = custo / 1000000;
				//campanha.setOrcamentoTotalExecutado(custo);
				//campanha.setQuantidadeImpressao(impressao);
				//campanha.setQuantidadeClique(click);
			}

			} catch (ReportException e) {
			e.printStackTrace();
		} catch (ReportDownloadResponseException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}
	
	public void atualizaResultado(CampanhaAds campanha) {
		this.campanha = campanha;
		super.executa();
	}

}
