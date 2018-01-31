/**
 *
 */
package com.hybris.b2b.core.attributehandlers;

import de.hybris.platform.core.model.product.ProductModel;
import de.hybris.platform.servicelayer.model.attribute.DynamicAttributeHandler;
import de.hybris.platform.util.Config;

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
		 *
		 * product.imagemgmt.service=cloudinary product.imagemgmt.prefixurl=https://res.cloudinary.com/
		 * product.imagemgmt.cloudname=knackhy product.imagemgmt.resourceType=image product.imagemgmt.type=upload
		 * product.imagemgmt.version=v1516391540 product.imagemgmt.imgFormat=jpg
		 */
		final String scale = "c_scale,h_200,w_300";
		final String filename = prodModel.getCode() + "." + Config.getString("product.imagemgmt.imgFormat", "");
		final String urlString = Config.getString("product.imagemgmt.prefixurl", "") + "/"
				+ Config.getString("product.imagemgmt.cloudname", "") + "/" + Config.getString("product.imagemgmt.resourceType", "")
				+ "/" + Config.getString("product.imagemgmt.type", "") + "/" + scale + "/" + filename;
		System.out.println("new product url is" + urlString + "with product name as" + prodModel.getName());
		/*
		 * try { final URL url = new URL(urlString); if (ImageIO.read(url).getWidth() > 0) { return urlString; } else {
		 * return null; } } catch (final IOException e) { // YTODO Auto-generated catch block e.printStackTrace(); return
		 * null; }
		 */
		/*
		 * "superZoom" - "1200Wx1200H" "zoom" - "515Wx515H" "store" -"365Wx246H" "product" - "300Wx300H" "thumbnail" -
		 * "96Wx96H" "cartIcon" - "65Wx65H" "styleSwatch" - "30Wx30H"
		 */
		//not to disturb he current flow...
		final Map prodUrls = new HashMap();
		final List gallery = new ArrayList();
		prodUrls.put("superZoom", this.buildUrlString(filename, "c_scale,h_1200,w_1200"));
		prodUrls.put("zoom", this.buildUrlString(filename, "c_scale,h_512,w_512"));
		prodUrls.put("store", this.buildUrlString(filename, "c_scale,h_246,w_365"));
		prodUrls.put("product", this.buildUrlString(filename, "c_scale,h_300,w_300"));
		prodUrls.put("thumbnail", this.buildUrlString(filename, "c_scale,h_96,w_96"));
		prodUrls.put("cartIcon", this.buildUrlString(filename, "c_scale,h_65,w_65"));
		prodUrls.put("styleSwatch", this.buildUrlString(filename, "c_scale,h_30,w_30"));
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
