package br.com.digicom.adsservice;

import java.rmi.RemoteException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import br.com.digicom.AdsService;
import br.com.digicom.modelo.CampanhaAds;
import br.com.digicom.modelo.CampanhaAnuncioResultado;
import br.com.digicom.modelo.PalavraChaveAds;

import com.beust.jcommander.Parameter;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroup;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAd;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAdOperation;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAdReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAdRotationMode;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAdServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupAdStatus;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupCriterion;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupCriterionOperation;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupOperation;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.AdGroupStatus;
import com.google.api.ads.adwords.axis.v201802.cm.AdRotationMode;
import com.google.api.ads.adwords.axis.v201802.cm.AdvertisingChannelType;
import com.google.api.ads.adwords.axis.v201802.cm.ApiException;
import com.google.api.ads.adwords.axis.v201802.cm.BiddableAdGroupCriterion;
import com.google.api.ads.adwords.axis.v201802.cm.BiddingStrategyConfiguration;
import com.google.api.ads.adwords.axis.v201802.cm.BiddingStrategyType;
import com.google.api.ads.adwords.axis.v201802.cm.Budget;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetBudgetDeliveryMethod;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetOperation;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.Campaign;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterion;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionOperation;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignOperation;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201802.cm.Criterion;
import com.google.api.ads.adwords.axis.v201802.cm.ExpandedTextAd;
import com.google.api.ads.adwords.axis.v201802.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201802.cm.GeoTargetTypeSetting;
import com.google.api.ads.adwords.axis.v201802.cm.GeoTargetTypeSettingPositiveGeoTargetType;
import com.google.api.ads.adwords.axis.v201802.cm.Keyword;
import com.google.api.ads.adwords.axis.v201802.cm.KeywordMatchType;
import com.google.api.ads.adwords.axis.v201802.cm.Language;
import com.google.api.ads.adwords.axis.v201802.cm.Level;
import com.google.api.ads.adwords.axis.v201802.cm.Location;
import com.google.api.ads.adwords.axis.v201802.cm.ManualCpcBiddingScheme;
import com.google.api.ads.adwords.axis.v201802.cm.Money;
import com.google.api.ads.adwords.axis.v201802.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201802.cm.Operator;
import com.google.api.ads.adwords.axis.v201802.cm.Setting;
import com.google.api.ads.adwords.axis.v201802.cm.TimeUnit;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;
import com.google.api.ads.adwords.lib.utils.examples.ArgumentNames;
import com.google.api.ads.common.lib.utils.examples.CodeSampleParams;

public class CampanhaAdsService extends AdsService {

	private CampanhaAds campanha = null;

	@Override
	protected void runExample(AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws RemoteException, ApiException {

		CampaignServiceInterface campaignService = adWordsServices.get(session, CampaignServiceInterface.class);

		// BiddingStrategyConfiguration
		BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
		biddingStrategyConfiguration.setBiddingStrategyType(BiddingStrategyType.TARGET_SPEND);

		// Money
		Money budgetAmount = new Money();
		budgetAmount.setMicroAmount(1_100_000L);

		// Budget
		Budget budget = new Budget();
		budget.setAmount(budgetAmount);
		budget.setIsExplicitlyShared(false);
		Long budgetId = this.criaBudget(budget, adWordsServices, session);
		budget.setBudgetId(budgetId);

		// Campaign
		Campaign campaign = new Campaign();
		campaign.setName(campanha.getNome() + "__" + System.currentTimeMillis());
		campaign.setStatus(CampaignStatus.PAUSED);
		campaign.setStartDate(this.converteData(this.getDataInicial()));
		campaign.setEndDate(this.converteData(this.getProximaSexta()));
		campaign.setAdvertisingChannelType(AdvertisingChannelType.SEARCH);
		campaign.setBiddingStrategyConfiguration(biddingStrategyConfiguration);
		campaign.setBudget(budget);

		// Localizacao
		GeoTargetTypeSetting geoTarget = new GeoTargetTypeSetting();
		geoTarget.setPositiveGeoTargetType(GeoTargetTypeSettingPositiveGeoTargetType.LOCATION_OF_PRESENCE);
		campaign.setSettings(new Setting[] { geoTarget });

		CampaignOperation operation = new CampaignOperation();
		operation.setOperand(campaign);
		operation.setOperator(Operator.ADD);
		CampaignOperation[] operations = new CampaignOperation[] { operation };
		CampaignReturnValue result = campaignService.mutate(operations);
		for (Campaign campaignResult : result.getValue()) {
			System.out.printf("Campanha com nome '%s' e ID %d foi criada.%n", campaignResult.getName(),
					campaignResult.getId());
			this.criaSegmentacaoLocal(campaignResult.getId(), adWordsServices, session);
			this.criarGrupoAnuncio(campanha, campaignResult.getId(), adWordsServices, session);
			campanha.setIdAds("" + campaignResult.getId());
			campanha.setDataInicial(this.converteDataInicioDia(this.getDataInicial()));
			campanha.setDataFinal(this.converteDataFinalDia(this.getProximaSexta()));
		}

	}

	// Portuguese,pt,1014
	private void criaSegmentacaoLocal(Long idCampanha, AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws ApiException, RemoteException {
		CampaignCriterionServiceInterface campaignCriterionService = adWordsServices.get(session,
				CampaignCriterionServiceInterface.class);

		// Create locations. The IDs can be found in the documentation or
		// retrieved with the LocationCriterionService.

		Location pais = new Location();
		pais.setId(2076L);
		Language lingua = new Language();
		lingua.setId(1014L);

		List operations = new ArrayList();
		for (Criterion criterion : new Criterion[] { pais, lingua }) {
			CampaignCriterionOperation operation = new CampaignCriterionOperation();
			CampaignCriterion campaignCriterion = new CampaignCriterion();
			campaignCriterion.setCampaignId(idCampanha);
			campaignCriterion.setCriterion(criterion);
			operation.setOperand(campaignCriterion);
			operation.setOperator(Operator.ADD);
			operations.add(operation);
		}

		CampaignCriterionReturnValue result = campaignCriterionService.mutate(
				(CampaignCriterionOperation[]) operations.toArray(new CampaignCriterionOperation[operations.size()]));
	}

	private Long criaBudget(Budget budget, AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws RemoteException, ApiException {
		BudgetOperation budgetOperation = new BudgetOperation();
		budgetOperation.setOperand(budget);
		budgetOperation.setOperator(Operator.ADD);

		BudgetServiceInterface budgetService = adWordsServices.get(session, BudgetServiceInterface.class);
		// Add the budget
		Long budgetId = budgetService.mutate(new BudgetOperation[] { budgetOperation }).getValue(0).getBudgetId();
		return budgetId;
	}

	private static class AddAdGroupsParams extends CodeSampleParams {
		@Parameter(names = ArgumentNames.CAMPAIGN_ID, required = true)
		private Long campaignId;
	}

	private static class AddExpandedTextAdsParams extends CodeSampleParams {
		@Parameter(names = ArgumentNames.AD_GROUP_ID, required = true)
		private Long adGroupId;
	}

	private static class AddKeywordsParams extends CodeSampleParams {
		@Parameter(names = ArgumentNames.AD_GROUP_ID, required = true)
		private Long adGroupId;
	}

	private void criaAnuncio(CampanhaAds campanha, Long idGrupo, AdWordsServicesInterface adWordsServices,
			AdWordsSession session) throws ApiException, RemoteException {

		AddExpandedTextAdsParams params = new AddExpandedTextAdsParams();
		params.adGroupId = idGrupo;

		// Get the AdGroupAdService.
		AdGroupAdServiceInterface adGroupAdService = adWordsServices.get(session, AdGroupAdServiceInterface.class);

		List<AdGroupAdOperation> operations = new ArrayList<>();

		List<CampanhaAnuncioResultado> anuncios = campanha.getAnuncioAds();
		for (CampanhaAnuncioResultado anuncio : anuncios) {
			// Create expanded text ad.
			ExpandedTextAd expandedTextAd = new ExpandedTextAd();
			expandedTextAd.setHeadlinePart1(anuncio.getAnuncioAds().getTitulo1());
			expandedTextAd.setHeadlinePart2(anuncio.getAnuncioAds().getTitulo2());
			expandedTextAd.setDescription(anuncio.getAnuncioAds().getDescricao1());
			expandedTextAd.setFinalUrls(new String[] { campanha.getUrlAlvo() });

			// Create ad group ad.
			AdGroupAd expandedTextAdGroupAd = new AdGroupAd();
			expandedTextAdGroupAd.setAdGroupId(idGrupo);
			expandedTextAdGroupAd.setAd(expandedTextAd);
			
		   

			// Optional: set the status.
			expandedTextAdGroupAd.setStatus(AdGroupAdStatus.ENABLED);

			// Create the operation.
			AdGroupAdOperation adGroupAdOperation = new AdGroupAdOperation();
			adGroupAdOperation.setOperand(expandedTextAdGroupAd);
			adGroupAdOperation.setOperator(Operator.ADD);

			operations.add(adGroupAdOperation);
		}
		// Add ads.
		AdGroupAdReturnValue result = adGroupAdService
				.mutate(operations.toArray(new AdGroupAdOperation[operations.size()]));

		// Display ads.
		int posicao = 0;
		for (AdGroupAd adGroupAdResult : result.getValue()) {
			ExpandedTextAd newAd = (ExpandedTextAd) adGroupAdResult.getAd();
			System.out.printf("Expanded text ad with ID %d and headline '%s - %s' was added.%n", newAd.getId(),
					newAd.getHeadlinePart1(), newAd.getHeadlinePart2());
			// Nao tenho certeza da ordem
			campanha.getAnuncioAds().get(posicao).setIdAds("" + newAd.getId());
			posicao++;
		}

	}

	private void criaPalavraChave(CampanhaAds campanha, Long idGrupo, AdWordsServicesInterface adWordsServices,
			AdWordsSession session) throws ApiException, RemoteException {
		AddKeywordsParams params = new AddKeywordsParams();
		params.adGroupId = idGrupo;
		AdGroupCriterionServiceInterface adGroupCriterionService = adWordsServices.get(session,
				AdGroupCriterionServiceInterface.class);

		List<AdGroupCriterionOperation> listaOperacao = new ArrayList<AdGroupCriterionOperation>();

		for (PalavraChaveAds palavra : campanha.getPalavraChaveAds()) {

			// Create keywords.
			Keyword keyword1 = new Keyword();
			keyword1.setText(palavra.getPalavra());
			keyword1.setMatchType(KeywordMatchType.PHRASE);
			// Create biddable ad group criterion.
			BiddableAdGroupCriterion keywordBiddableAdGroupCriterion1 = new BiddableAdGroupCriterion();
			keywordBiddableAdGroupCriterion1.setAdGroupId(idGrupo);
			keywordBiddableAdGroupCriterion1.setCriterion(keyword1);
			// Create operations.
			AdGroupCriterionOperation keywordAdGroupCriterionOperation1 = new AdGroupCriterionOperation();
			keywordAdGroupCriterionOperation1.setOperand(keywordBiddableAdGroupCriterion1);
			keywordAdGroupCriterionOperation1.setOperator(Operator.ADD);
			listaOperacao.add(keywordAdGroupCriterionOperation1);

			/*
			Keyword keyword2 = new Keyword();
			keyword2.setText(palavra.getPalavra());
			keyword2.setMatchType(KeywordMatchType.PHRASE);
			BiddableAdGroupCriterion keywordBiddableAdGroupCriterion2 = new BiddableAdGroupCriterion();
			keywordBiddableAdGroupCriterion2.setAdGroupId(idGrupo);
			keywordBiddableAdGroupCriterion2.setCriterion(keyword2);
			AdGroupCriterionOperation keywordAdGroupCriterionOperation2 = new AdGroupCriterionOperation();
			keywordAdGroupCriterionOperation2.setOperand(keywordBiddableAdGroupCriterion2);
			keywordAdGroupCriterionOperation2.setOperator(Operator.ADD);
			listaOperacao.add(keywordAdGroupCriterionOperation2);
			*/
		}
		// AdGroupCriterionOperation keywordAdGroupCriterionOperation2 = new
		// AdGroupCriterionOperation();
		// keywordAdGroupCriterionOperation2.setOperand(keywordNegativeAdGroupCriterion2);
		// keywordAdGroupCriterionOperation2.setOperator(Operator.ADD);

		// AdGroupCriterionOperation[] operations = new
		// AdGroupCriterionOperation[] { keywordAdGroupCriterionOperation1 };
		AdGroupCriterionOperation[] operations = listaOperacao.toArray(new AdGroupCriterionOperation[0]);

		// Add keywords.
		AdGroupCriterionReturnValue result = adGroupCriterionService.mutate(operations);

		// Display results.
		for (AdGroupCriterion adGroupCriterionResult : result.getValue()) {
			System.out.printf(
					"Keyword ad group criterion with ad group ID %d, criterion ID %d, "
							+ "text '%s', and match type '%s' was added.%n",
					adGroupCriterionResult.getAdGroupId(), adGroupCriterionResult.getCriterion().getId(),
					((Keyword) adGroupCriterionResult.getCriterion()).getText(),
					((Keyword) adGroupCriterionResult.getCriterion()).getMatchType());
		}

	}

	private void criarGrupoAnuncio(CampanhaAds campanha, Long idCampanha, AdWordsServicesInterface adWordsServices,
			AdWordsSession session) throws RemoteException, ApiException {
		AddAdGroupsParams params = new AddAdGroupsParams();

		params.campaignId = idCampanha;
		// Get the AdGroupService.
		AdGroupServiceInterface adGroupService = adWordsServices.get(session, AdGroupServiceInterface.class);

		// Create ad group.
		AdGroup adGroup = new AdGroup();
		adGroup.setName("Grupo Unico" + System.currentTimeMillis());
		adGroup.setStatus(AdGroupStatus.ENABLED);
		adGroup.setCampaignId(idCampanha);
		 // Set the rotation mode.
	    AdGroupAdRotationMode rotationMode = new AdGroupAdRotationMode(AdRotationMode.ROTATE_FOREVER);
	    adGroup.setAdGroupAdRotationMode(rotationMode);

		
		AdGroupOperation operation = new AdGroupOperation();
		operation.setOperand(adGroup);
		operation.setOperator(Operator.ADD);

		AdGroupOperation[] operations = new AdGroupOperation[] { operation };

		// Add ad groups.
		AdGroupReturnValue result = adGroupService.mutate(operations);

		// Display new ad groups.
		for (AdGroup adGroupResult : result.getValue()) {
			System.out.printf("Ad group with name '%s' and ID %d was added.%n", adGroupResult.getName(),
					adGroupResult.getId());
			this.criaAnuncio(campanha, adGroupResult.getId(), adWordsServices, session);
			this.criaPalavraChave(campanha, adGroupResult.getId(), adWordsServices, session);
		}

	}

	protected void runExampleNaoUsado(AdWordsServicesInterface adWordsServices, AdWordsSession session)
			throws RemoteException, ApiException {
		BudgetServiceInterface budgetService = adWordsServices.get(session, BudgetServiceInterface.class);

		// Create a budget, which can be shared by multiple campaigns.
		Budget sharedBudget = new Budget();
		sharedBudget.setName("Bud_" + campanha.getNome());

		Money budgetAmount = new Money();
		budgetAmount.setMicroAmount(1_100_000L);
		sharedBudget.setAmount(budgetAmount);
		sharedBudget.setDeliveryMethod(BudgetBudgetDeliveryMethod.STANDARD);

		BudgetOperation budgetOperation = new BudgetOperation();
		budgetOperation.setOperand(sharedBudget);
		budgetOperation.setOperator(Operator.ADD);

		// Add the budget
		Long budgetId = budgetService.mutate(new BudgetOperation[] { budgetOperation }).getValue(0).getBudgetId();

		// Get the CampaignService.
		CampaignServiceInterface campaignService = adWordsServices.get(session, CampaignServiceInterface.class);

		// Create campaign.
		Campaign campaign = new Campaign();
		campaign.setName(campanha.getNome());

		// Recommendation: Set the campaign to PAUSED when creating it to
		// prevent
		// the ads from immediately serving. Set to ENABLED once you've added
		// targeting and the ads are ready to serve.
		campaign.setStatus(CampaignStatus.PAUSED);

		BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
		biddingStrategyConfiguration.setBiddingStrategyType(BiddingStrategyType.MANUAL_CPC);

		// You can optionally provide a bidding scheme in place of the type.
		ManualCpcBiddingScheme cpcBiddingScheme = new ManualCpcBiddingScheme();
		biddingStrategyConfiguration.setBiddingScheme(cpcBiddingScheme);

		campaign.setBiddingStrategyConfiguration(biddingStrategyConfiguration);

		// You can optionally provide these field(s).
		campaign.setStartDate(converteData(getDataInicial()));
		campaign.setEndDate(converteData(getProximaSexta()));
		campaign.setFrequencyCap(new FrequencyCap(5L, TimeUnit.DAY, Level.ADGROUP));

		// Only the budgetId should be sent, all other fields will be ignored by
		// CampaignService.
		Budget budget = new Budget();
		budget.setBudgetId(budgetId);
		campaign.setBudget(budget);

		campaign.setAdvertisingChannelType(AdvertisingChannelType.SEARCH);

		// Set the campaign network options to Search and Search Network.
		NetworkSetting networkSetting = new NetworkSetting();
		networkSetting.setTargetGoogleSearch(true);
		networkSetting.setTargetSearchNetwork(true);
		networkSetting.setTargetContentNetwork(false);
		networkSetting.setTargetPartnerSearchNetwork(false);
		campaign.setNetworkSetting(networkSetting);

		// Set options that are not required.
		GeoTargetTypeSetting geoTarget = new GeoTargetTypeSetting();
		geoTarget.setPositiveGeoTargetType(GeoTargetTypeSettingPositiveGeoTargetType.DONT_CARE);
		campaign.setSettings(new Setting[] { geoTarget });

		// Create operations.
		CampaignOperation operation = new CampaignOperation();
		operation.setOperand(campaign);
		operation.setOperator(Operator.ADD);

		CampaignOperation[] operations = new CampaignOperation[] { operation };

		// Add campaigns.
		CampaignReturnValue result = campaignService.mutate(operations);

		// Display campaigns.
		for (Campaign campaignResult : result.getValue()) {
			System.out.printf("Campanha com nome name '%s' e ID %d foi criada.%n", campaignResult.getName(),
					campaignResult.getId());
			campanha.setIdAds("" + campaignResult.getId());
		}

	}

	public void cria(CampanhaAds campanha) {
		// TODO Auto-generated method stub
		this.campanha = campanha;
		super.executa();
	}

	public Calendar getDataInicial() {
		Calendar date1 = Calendar.getInstance();
		date1.add(Calendar.DATE, 1);
		while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
			date1.add(Calendar.DATE, 1);
		}
		return date1;
	}

	private String converteData(Calendar data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		return sdf.format(data.getTime());
	}

	private String converteDataInicioDia(Calendar data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dia = sdf.format(data.getTime());
		return dia;
	}

	private String converteDataFinalDia(Calendar data) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String dia = sdf.format(data.getTime());
		return dia;
	}

	public Calendar getProximaSexta() {
		Calendar date1 = getDataInicial();
		date1.add(Calendar.DATE, 1);

		while (date1.get(Calendar.DAY_OF_WEEK) != Calendar.FRIDAY) {
			date1.add(Calendar.DATE, 1);
		}
		return date1;

	}

}
