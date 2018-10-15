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

public class CampanhaResultService extends AdsService {

	@Override
	protected void runExample(AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws RemoteException, ApiException {
		// TODO Auto-generated method stub
		// Create the query.
		String query = "SELECT Id, AdNetworkType1, Impressions " + "FROM CRITERIA_PERFORMANCE_REPORT "
				+ "WHERE Status IN [ENABLED] " + "DURING LAST_7_DAYS";
		
		query = "SELECT Id, Criteria, AdGroupName, Impressions FROM KEYWORDS_PERFORMANCE_REPORT DURING LAST_7_DAYS";

		// Optional: Set the reporting configuration of the session to suppress header,
		// column name, or
		// summary rows in the report output. You can also configure this via your
		// ads.properties
		// configuration file. See AdWordsSession.Builder.from(Configuration) for
		// details.
		// In addition, you can set whether you want to explicitly include or exclude
		// zero impression
		// rows.
		ReportingConfiguration reportingConfiguration = new ReportingConfiguration.Builder()
				// Skip all header and summary lines since the loop below expects
				// every field to be present in each line.
				.skipReportHeader(true).skipColumnHeader(true).skipReportSummary(true)
				// Enable to include rows with zero impressions.
				.includeZeroImpressions(false).build();
		session.setReportingConfiguration(reportingConfiguration);

		ReportDownloaderInterface reportDownloader = adWordsServices.getUtility(session,ReportDownloaderInterface.class);

		BufferedReader reader = null;
		try {
			// Set the property api.adwords.reportDownloadTimeout or call
			// ReportDownloader.setReportDownloadTimeout to set a timeout (in milliseconds)
			// for CONNECT and READ in report downloads.
			final ReportDownloadResponse response = reportDownloader.downloadReport(query, DownloadFormat.CSV);

			// Read the response as a BufferedReader.
			reader = new BufferedReader(new InputStreamReader(response.getInputStream(), UTF_8));

			// Map to store total impressions by ad network type 1.
			Map<String, Long> impressionsByAdNetworkType1 = Maps.newTreeMap();

			// Stream the results one line at a time and perform any line-specific
			// processing.
			String line;
			Splitter splitter = Splitter.on(',');
			while ((line = reader.readLine()) != null) {
				System.out.println(line);

				// Split the line into a list of field values.
				List<String> values = splitter.splitToList(line);

				// Update the total impressions for the ad network type 1 value.
				String adNetworkType1 = values.get(1);
				Long impressions = Longs.tryParse(values.get(2));
				if (impressions != null) {
					Long impressionsTotal = impressionsByAdNetworkType1.get(adNetworkType1);
					impressionsTotal = impressionsTotal == null ? 0L : impressionsTotal;
					impressionsByAdNetworkType1.put(adNetworkType1, impressionsTotal + impressions);
				}
			}

			// Print the impressions totals by ad network type 1.
			System.out.println();
			System.out.printf("Total impressions by ad network type 1:%n%s%n",
					Joiner.on(SystemUtils.LINE_SEPARATOR).join(impressionsByAdNetworkType1.entrySet()));
		} catch (ReportException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ReportDownloadResponseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			if (reader != null) {
				try {
					reader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
	}

}
