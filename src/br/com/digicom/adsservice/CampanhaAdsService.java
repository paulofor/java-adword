package br.com.digicom.adsservice;

import java.rmi.RemoteException;
import java.util.ArrayList;
import java.util.List;

import org.joda.time.DateTime;

import com.google.api.ads.adwords.axis.v201802.cm.AdvertisingChannelType;
import com.google.api.ads.adwords.axis.v201802.cm.ApiException;
import com.google.api.ads.adwords.axis.v201802.cm.BiddingStrategyConfiguration;
import com.google.api.ads.adwords.axis.v201802.cm.BiddingStrategyType;
import com.google.api.ads.adwords.axis.v201802.cm.Budget;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetBudgetDeliveryMethod;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetOperation;
import com.google.api.ads.adwords.axis.v201802.cm.BudgetServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.Campaign;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignOperation;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignStatus;
import com.google.api.ads.adwords.axis.v201802.cm.FrequencyCap;
import com.google.api.ads.adwords.axis.v201802.cm.GeoTargetTypeSetting;
import com.google.api.ads.adwords.axis.v201802.cm.GeoTargetTypeSettingPositiveGeoTargetType;
import com.google.api.ads.adwords.axis.v201802.cm.Level;
import com.google.api.ads.adwords.axis.v201802.cm.ManualCpcBiddingScheme;
import com.google.api.ads.adwords.axis.v201802.cm.Money;
import com.google.api.ads.adwords.axis.v201802.cm.NetworkSetting;
import com.google.api.ads.adwords.axis.v201802.cm.Operator;
import com.google.api.ads.adwords.axis.v201802.cm.Setting;
import com.google.api.ads.adwords.axis.v201802.cm.TimeUnit;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterion;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionOperation;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionReturnValue;
import com.google.api.ads.adwords.axis.v201802.cm.CampaignCriterionServiceInterface;
import com.google.api.ads.adwords.axis.v201802.cm.Criterion;
import com.google.api.ads.adwords.axis.v201802.cm.Location;
import com.google.api.ads.adwords.lib.client.AdWordsSession;
import com.google.api.ads.adwords.lib.factory.AdWordsServicesInterface;
import com.google.api.client.util.Lists;

import br.com.digicom.AdsService;
import br.com.digicom.modelo.CampanhaAds;

public class CampanhaAdsService extends AdsService {

	private CampanhaAds campanha = null;

	@Override
	protected void runExample(AdWordsServicesInterface adWordsServices,
			AdWordsSession session) throws RemoteException, ApiException {


		CampaignServiceInterface campaignService = adWordsServices.get(session,	CampaignServiceInterface.class);


		BiddingStrategyConfiguration biddingStrategyConfiguration = new BiddingStrategyConfiguration();
		biddingStrategyConfiguration.setBiddingStrategyType(BiddingStrategyType.TARGET_SPEND);

		Money budgetAmount = new Money();
		budgetAmount.setMicroAmount(1_100_000L);
		Budget budget = new Budget();
		budget.setAmount(budgetAmount);
		budget.setIsExplicitlyShared(false);
		
		Long budgetId = this.criaBudget(budget, adWordsServices, session);
		budget.setBudgetId(budgetId);
		
		Campaign campaign = new Campaign();
		campaign.setName(campanha.getNome() + "__" + System.currentTimeMillis());
		campaign.setStatus(CampaignStatus.PAUSED);
		campaign.setStartDate(new DateTime().plusDays(1).toString("yyyyMMdd"));
		campaign.setEndDate(new DateTime().plusDays(8).toString("yyyyMMdd"));
		campaign.setAdvertisingChannelType(AdvertisingChannelType.SEARCH);
		campaign.setBiddingStrategyConfiguration(biddingStrategyConfiguration);
		campaign.setBudget(budget);
		
		// Localizacao
	    GeoTargetTypeSetting geoTarget = new GeoTargetTypeSetting();
	    geoTarget.setPositiveGeoTargetType(GeoTargetTypeSettingPositiveGeoTargetType.AREA_OF_INTEREST);
	    campaign.setSettings(new Setting[] {geoTarget});
	    

		// Create operations.
		CampaignOperation operation = new CampaignOperation();
		operation.setOperand(campaign);
		operation.setOperator(Operator.ADD);
		CampaignOperation[] operations = new CampaignOperation[] { operation };

		// Add campaigns.
		CampaignReturnValue result = campaignService.mutate(operations);

		// Display campaigns.
		for (Campaign campaignResult : result.getValue()) {
			System.out.printf("Campanha com nome '%s' e ID %d foi criada.%n", campaignResult.getName(), campaignResult.getId());
			this.criaSegmentacaoLocal(campaignResult.getId(), adWordsServices, session);
		}

	}
	
	
	// Portuguese,pt,1014
	private void criaSegmentacaoLocal(Long idCampanha, AdWordsServicesInterface adWordsServices, AdWordsSession session) throws ApiException, RemoteException {
		CampaignCriterionServiceInterface campaignCriterionService = adWordsServices.get(session,	CampaignCriterionServiceInterface.class);
		
		// Create locations. The IDs can be found in the documentation or
		// retrieved with the LocationCriterionService.

		Location pais = new Location();
		pais.setId(2076L);

		List operations = new ArrayList();
		for (Criterion criterion : new Criterion[] {pais}) {
		  CampaignCriterionOperation operation = new CampaignCriterionOperation();
		  CampaignCriterion campaignCriterion = new CampaignCriterion();
		  campaignCriterion.setCampaignId(idCampanha);
		  campaignCriterion.setCriterion(criterion);
		  operation.setOperand(campaignCriterion);
		  operation.setOperator(Operator.ADD);
		  operations.add(operation);
		}

		CampaignCriterionReturnValue result = campaignCriterionService.mutate((CampaignCriterionOperation[]) operations.toArray(new CampaignCriterionOperation[operations.size()]));
	}

	private Long criaBudget(Budget budget,AdWordsServicesInterface adWordsServices,	AdWordsSession session) throws RemoteException, ApiException {
		BudgetOperation budgetOperation = new BudgetOperation();
		budgetOperation.setOperand(budget);
		budgetOperation.setOperator(Operator.ADD);

		BudgetServiceInterface budgetService = adWordsServices.get(session,	BudgetServiceInterface.class);
		// Add the budget
		Long budgetId = budgetService.mutate(new BudgetOperation[] { budgetOperation }).getValue(0).getBudgetId();
		return budgetId;
	}
	
	
	
	protected void runExample2(AdWordsServicesInterface adWordsServices,
			AdWordsSession session) throws RemoteException, ApiException {
		BudgetServiceInterface budgetService = adWordsServices.get(session,	BudgetServiceInterface.class);

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
		Long budgetId = budgetService
				.mutate(new BudgetOperation[] { budgetOperation }).getValue(0)
				.getBudgetId();

		// Get the CampaignService.
		CampaignServiceInterface campaignService = adWordsServices.get(session,
				CampaignServiceInterface.class);

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
		campaign.setStartDate(new DateTime().plusDays(1).toString("yyyyMMdd"));
		campaign.setEndDate(new DateTime().plusDays(8).toString("yyyyMMdd"));
		campaign.setFrequencyCap(new FrequencyCap(5L, TimeUnit.DAY,
				Level.ADGROUP));

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
			System.out.printf(
					"Campanha com nome name '%s' e ID %d foi criada.%n",
					campaignResult.getName(), campaignResult.getId());
		}

	}

	public void cria(CampanhaAds campanha) {
		// TODO Auto-generated method stub
		this.campanha = campanha;
		super.executa();
	}

}
