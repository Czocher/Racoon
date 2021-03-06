package org.czocher.raccoon.views.product.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.product.ProductPresenter;
import org.czocher.raccoon.views.product.ProductDeleteView;
import org.czocher.raccoon.views.product.ProductEditView;
import org.czocher.raccoon.views.product.ProductView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProductViewImpl implements ProductView {

	private ProductPresenter presenter;
	private Template template;

	public ProductViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("product/product.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("productEditPath", ProductEditView.TAG);
		values.put("productDeletePath", ProductDeleteView.TAG);
		values.put("product", presenter.getProduct());

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ProductPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ProductPresenter presenter) {
		this.presenter = presenter;
	}

}
