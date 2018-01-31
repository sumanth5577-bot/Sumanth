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
package com.hybris.b2b.fulfilmentprocess.jalo;

import de.hybris.platform.jalo.JaloSession;
import de.hybris.platform.jalo.extension.ExtensionManager;
import com.hybris.b2b.fulfilmentprocess.constants.Knackb2bFulfilmentProcessConstants;

@SuppressWarnings("PMD")
public class Knackb2bFulfilmentProcessManager extends GeneratedKnackb2bFulfilmentProcessManager
{
	public static final Knackb2bFulfilmentProcessManager getInstance()
	{
		ExtensionManager em = JaloSession.getCurrentSession().getExtensionManager();
		return (Knackb2bFulfilmentProcessManager) em.getExtension(Knackb2bFulfilmentProcessConstants.EXTENSIONNAME);
	}
	
}
