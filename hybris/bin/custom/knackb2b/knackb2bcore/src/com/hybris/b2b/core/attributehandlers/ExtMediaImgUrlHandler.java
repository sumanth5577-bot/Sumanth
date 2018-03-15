/**
 *
 */
package com.hybris.b2b.core.attributehandlers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.Config;

import java.net.MalformedURLException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author bharathy
 *
 */
public class ExtMediaImgUrlHandler implements DynamicAttributeHandler<Map, ProductModel>
{
	public static final String SCALE_H200W300 = "c_scale,h_200,w_300";
	public static final String SCALE_H1200W1200 = "c_scale,h_1200,w_1200";
	public static final String SCALE_H512W512 = "c_scale,h_512,w_512";
	public static final String SCALE_H246W365 = "c_scale,h_246,w_365";
	public static final String SCALE_H300W300 = "c_scale,h_300,w_300";
	public static final String SCALE_H96W96 = "c_scale,h_96,w_96";
	public static final String SCALE_H65W65 = "c_scale,h_65,w_65";
	public static final String SCALE_H30W30 = "c_scale,h_30,w_30";

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#get(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel)
	 */
	@Override
	public Map get(final ProductModel prodModel)
	{
		/*
		 * #https://res.cloudinary.com/<cloud_name>/<resource_type>/<type>/<version>/<public_id>.<format>
		 * #http://res.cloudinary.com/knackhy/image/upload/c_scale,h_200,w_300/v1516652821/sunflower.jpg
		 * product.imagemgmt.service=cloudinary product.imagemgmt.prefixurl=https://res.cloudinary.com/
		 * product.imagemgmt.cloudname=knackhy product.imagemgmt.resourceType=image product.imagemgmt.type=upload
		 * product.imagemgmt.version=v1516391540 product.imagemgmt.imgFormat=jpg
		 */
		final String scale = "c_scale,h_200,w_300";
		final String filename = prodModel.getExtImageFileName();// this should get the extension also ..
		//final String filename = prodModel.getCode() + "." + Config.getString("product.imagemgmt.imgFormat", "");
		final String urlString = Config.getString("product.imagemgmt.prefixurl", "") + "/"
				+ Config.getString("product.imagemgmt.cloudname", "") + "/" + Config.getString("product.imagemgmt.resourceType", "")
				+ "/" + Config.getString("product.imagemgmt.type", "") + "/" + scale + "/" + filename;
		System.out.println("new product url is" + urlString + "with product name as" + prodModel.getName());
		/*
		 * "superZoom" - "1200Wx1200H" "zoom" - "515Wx515H" "store" -"365Wx246H" "product" - "300Wx300H" "thumbnail" -
		 * "96Wx96H" "cartIcon" - "65Wx65H" "styleSwatch" - "30Wx30H"
		 */
		//not to disturb he current flow...
		final Map prodUrls = new HashMap();
		final List gallery = new ArrayList();
		String url = this.buildUrlString(filename, SCALE_H1200W1200);
		if (urlValidator(url))
		{
			prodUrls.put("superZoom", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H512W512);
		if (urlValidator(url))
		{
			prodUrls.put("zoom", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H246W365);
		if (urlValidator(url))
		{
			prodUrls.put("store", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H300W300);
		if (urlValidator(url))
		{
			prodUrls.put("product", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H96W96);
		if (urlValidator(url))
		{
			prodUrls.put("thumbnail", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H65W65);
		if (urlValidator(url))
		{
			prodUrls.put("cartIcon", url);
		}
		url = null;
		url = this.buildUrlString(filename, SCALE_H30W30);
		if (urlValidator(url))
		{
			prodUrls.put("styleSwatch", url);
		}
		prodUrls.put("gallery", gallery);
		return prodUrls;
	}

	/**
	 * @return
	 */
	private String buildUrlString(final String filename, final String scale1)
	{
		// YTODO Auto-generated method stub
		final String urlString1 = Config.getString("product.imagemgmt.prefixurl", "") + "/"
				+ Config.getString("product.imagemgmt.cloudname", "") + "/" + Config.getString("product.imagemgmt.resourceType", "")
				+ "/" + Config.getString("product.imagemgmt.type", "") + "/" + scale1 + "/" + filename;
		return urlString1;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel, java.lang.Object)
	 */
	@Override
	public void set(final ProductModel arg0, final Map arg1)
	{
		// YTODO Auto-generated method stub

	}

	public boolean urlValidator(final String url)
	{
		try
		{
			new URL(url).toURI();
			return true;
		}
		catch (final URISyntaxException exception)
		{
			exception.printStackTrace();
			return false;
		}
		catch (final MalformedURLException exception)
		{
			exception.printStackTrace();
			return false;
		}

	}
	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel, java.lang.Object)
	 */

	/*
	 * (non-Javadoc)
	 *
	 * @see
	 * de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler#set(de.hybris.platform.servicelayer.model.
	 * AbstractItemModel, java.lang.Object)
	 */

}
