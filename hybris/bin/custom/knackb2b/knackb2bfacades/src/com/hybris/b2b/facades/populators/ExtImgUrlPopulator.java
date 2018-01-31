/**
 *
 */
package com.hybris.b2b.facades.populators;

import de.hybris.platform.commercefacades.product.data.ProductData;
import de.hybris.platform.commerceservices.search.resultdata.SearchResultValueData;
import de.hybris.platform.converters.Populator;
import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.product.ProductService;
import de.hybris.platform.servicelayer.dto.converter.ConversionException;
import de.hybris.platform.servicelayer.exceptions.UnknownIdentifierException;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.springframework.util.Assert;


/**
 * @author bharathy
 *
 */
public class ExtImgUrlPopulator implements Populator<SearchResultValueData, ProductData>
{

	@SuppressWarnings("unused")
	private static final Logger LOG = Logger.getLogger(ExtImgUrlPopulator.class);
	// Map key to retrieve product code
	public static final String PRODUCT_CODE = "code";
	@Resource(name = "productService")
	private ProductService productService;


	@Override
	public void populate(final SearchResultValueData source, final ProductData productData) throws ConversionException
	{
		final String productCode = this.<String> getValue(source, PRODUCT_CODE);
		Assert.notNull(source, "Parameter source cannot be null.");
		Assert.notNull(productData, "Parameter target cannot be null.");
		try
		{
			final ProductModel model = getProductService().getProductForCode(productCode);
			productData.setExtMediaImgUrlList(model.getExtMediaImgUrl());
		}
		catch (final UnknownIdentifierException e)
		{
			LOG.error(String.format("Product with code [%s] not found", productCode));
		}
	}

	/**
	 * @return the productService
	 */
	public ProductService getProductService()
	{
		return productService;
	}

	/**
	 * @param productService
	 *           the productService to set
	 */
	public void setProductService(final ProductService productService)
	{
		this.productService = productService;
	}

	protected <T> T getValue(final SearchResultValueData source, final String propertyName)
	{
		if (source.getValues() == null)
		{
			return null;
		}

		// DO NOT REMOVE the cast (T) below, while it should be unnecessary it is required by the javac compiler
		return (T) source.getValues().get(propertyName);
	}


}