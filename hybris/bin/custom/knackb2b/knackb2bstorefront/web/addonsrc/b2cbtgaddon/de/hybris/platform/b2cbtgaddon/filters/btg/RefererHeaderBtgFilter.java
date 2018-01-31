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
package de.hybris.platform.b2cbtgaddon.filters.btg;

import de.hybris.platform.btg.events.AbstractBTGRuleDataEvent;
import de.hybris.platform.btg.events.RefererHeaderUsedBTGRuleDataEvent;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;


/**
 * FilterBean to produce the request scoped BTG event {@link RefererHeaderUsedBTGRuleDataEvent} This is a spring
 * configured filter that is executed by the PlatformFilterChain.
 */
public class RefererHeaderBtgFilter extends AbstractBtgFilter
{
	private static final String REFERER_HEADER_NAME = "Referer";

	@Override
	protected AbstractBTGRuleDataEvent getEvent(final HttpServletRequest request)
	{
		RefererHeaderUsedBTGRuleDataEvent result = null;
		final String referrer = request.getHeader(REFERER_HEADER_NAME);
		if (!StringUtils.isBlank(referrer))
		{
			result = new RefererHeaderUsedBTGRuleDataEvent(referrer);
		}
		return result;
	}

	@Override
	protected boolean isRequestScoped()
	{
		return true;
	}
}
