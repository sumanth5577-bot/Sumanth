/*
 * [y] hybris Platform
 *
 * Copyright (c) 2017 SAP SE or an SAP affiliate company.  All rights reserved.
 *
 * This software is the confidential and proprietary information of SAP
 * ("Confidential Information"). You shall not disclose such Confidential
 * Information and shall use it only in accordance with the terms of the
 * license agreement you entered into with SAP.
 */
package com.hybris.b2b.setup;

import static com.hybris.b2b.constants.Kb2bConstants.PLATFORM_LOGO_CODE;

import de.hybris.platform.catalog.CatalogVersionService;
import de.hybris.platform.commerceservices.setup.AbstractSystemSetup;
import de.hybris.platform.commerceservices.setup.SetupImpexService;
import de.hybris.platform.commerceservices.setup.SetupSolrIndexerService;
import de.hybris.platform.commerceservices.setup.SetupSyncJobService;
import de.hybris.platform.commerceservices.setup.data.ImportData;
import de.hybris.platform.commerceservices.setup.events.CoreDataImportedEvent;
import de.hybris.platform.commerceservices.setup.events.SampleDataImportedEvent;
import de.hybris.platform.core.initialization.SystemSetup;
import de.hybris.platform.core.initialization.SystemSetupContext;
import de.hybris.platform.core.initialization.SystemSetupParameter;
import de.hybris.platform.core.initialization.SystemSetupParameterMethod;
import de.hybris.platform.servicelayer.event.EventService;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Required;

import com.hybris.b2b.constants.Kb2bConstants;
import com.hybris.b2b.service.Kb2bService;
import com.hybris.b2b.services.dataimport.impl.Kb2bCoreDataImportService;
import com.hybris.b2b.services.dataimport.impl.Kb2bSampleDataImportService;


@SystemSetup(extension = Kb2bConstants.EXTENSIONNAME)
public class Kb2bSystemSetup extends AbstractSystemSetup
{
	public static final String KNACK_B2B = "knackb2b";

	private static final String IMPORT_CORE_DATA = "importCoreData";
	private static final String IMPORT_SAMPLE_DATA = "importSampleData";
	private static final String ACTIVATE_SOLR_CRON_JOBS = "activateSolrCronJobs";

	private Kb2bCoreDataImportService kb2bCoreDataImportService;
	private Kb2bSampleDataImportService kb2bSampleDataImportService;
	private final Kb2bService kb2bService;
	private SetupImpexService setupImpexService;
	private SetupSyncJobService setupSyncJobService;
	private SetupSolrIndexerService setupSolrIndexerService;
	private CatalogVersionService catalogVersionService;
	private EventService eventService;

	public Kb2bSystemSetup(final Kb2bService kb2bService)
	{
		this.kb2bService = kb2bService;
	}

	@SystemSetupParameterMethod
	@Override
	public List<SystemSetupParameter> getInitializationOptions()
	{
		final List<SystemSetupParameter> params = new ArrayList<SystemSetupParameter>();

		params.add(createBooleanSystemSetupParameter(IMPORT_CORE_DATA, "Import Core Data", true));
		params.add(createBooleanSystemSetupParameter(IMPORT_SAMPLE_DATA, "Import Sample Data", true));
		params.add(createBooleanSystemSetupParameter(ACTIVATE_SOLR_CRON_JOBS, "Activate Solr Cron Jobs", true));

		return params;
	}

	/**
	 * This method will be called during the system initialization.
	 *
	 * @param context
	 *           the context provides the selected parameters and values
	 */
	@SystemSetup(type = SystemSetup.Type.PROJECT, process = SystemSetup.Process.ALL)
	public void createProjectData(final SystemSetupContext context)
	{
		final List<ImportData> importData = new ArrayList<ImportData>();

		final ImportData kb2bImportData = new ImportData();
		kb2bImportData.setProductCatalogName(KNACK_B2B);
		kb2bImportData.setContentCatalogNames(Arrays.asList(KNACK_B2B));
		kb2bImportData.setStoreNames(Arrays.asList(KNACK_B2B));
		importData.add(kb2bImportData);

		getKb2bCoreDataImportService().execute(this, context, importData);
		getEventService().publishEvent(new CoreDataImportedEvent(context, importData));

		getKb2bSampleDataImportService().execute(this, context, importData);
		getKb2bSampleDataImportService().importCommerceOrgData(context);
		getEventService().publishEvent(new SampleDataImportedEvent(context, importData));
	}

	public Kb2bCoreDataImportService getKb2bCoreDataImportService()
	{
		return kb2bCoreDataImportService;
	}

	@Required
	public void setKb2bCoreDataImportService(final Kb2bCoreDataImportService kb2bCoreDataImportService)
	{
		this.kb2bCoreDataImportService = kb2bCoreDataImportService;
	}


	public Kb2bSampleDataImportService getKb2bSampleDataImportService()
	{
		return kb2bSampleDataImportService;
	}

	@Required
	public void setKb2bSampleDataImportService(final Kb2bSampleDataImportService kb2bSampleDataImportService)
	{
		this.kb2bSampleDataImportService = kb2bSampleDataImportService;
	}

	@SystemSetup(process = SystemSetup.Process.INIT, type = SystemSetup.Type.ESSENTIAL)
	public void createEssentialData()
	{
		kb2bService.createLogo(PLATFORM_LOGO_CODE);
	}

	private InputStream getImageStream()
	{
		return Kb2bSystemSetup.class.getResourceAsStream("/kb2b/sap-hybris-platform.png");
	}

	/**
	 * @return the setupImpexService
	 */
	@Override
	public SetupImpexService getSetupImpexService()
	{
		return setupImpexService;
	}

	/**
	 * @param setupImpexService
	 *           the setupImpexService to set
	 */
	@Override
	public void setSetupImpexService(final SetupImpexService setupImpexService)
	{
		this.setupImpexService = setupImpexService;
	}

	/**
	 * @return the setupSyncJobService
	 */
	@Override
	public SetupSyncJobService getSetupSyncJobService()
	{
		return setupSyncJobService;
	}

	/**
	 * @param setupSyncJobService
	 *           the setupSyncJobService to set
	 */
	@Override
	public void setSetupSyncJobService(final SetupSyncJobService setupSyncJobService)
	{
		this.setupSyncJobService = setupSyncJobService;
	}

	/**
	 * @return the setupSolrIndexerService
	 */
	@Override
	public SetupSolrIndexerService getSetupSolrIndexerService()
	{
		return setupSolrIndexerService;
	}

	/**
	 * @param setupSolrIndexerService
	 *           the setupSolrIndexerService to set
	 */
	@Override
	public void setSetupSolrIndexerService(final SetupSolrIndexerService setupSolrIndexerService)
	{
		this.setupSolrIndexerService = setupSolrIndexerService;
	}

	/**
	 * @return the catalogVersionService
	 */
	@Override
	public CatalogVersionService getCatalogVersionService()
	{
		return catalogVersionService;
	}

	/**
	 * @param catalogVersionService
	 *           the catalogVersionService to set
	 */
	@Override
	public void setCatalogVersionService(final CatalogVersionService catalogVersionService)
	{
		this.catalogVersionService = catalogVersionService;
	}

	/**
	 * @return the eventService
	 */
	@Override
	public EventService getEventService()
	{
		return eventService;
	}

	/**
	 * @param eventService
	 *           the eventService to set
	 */
	@Override
	public void setEventService(final EventService eventService)
	{
		this.eventService = eventService;
	}
}
