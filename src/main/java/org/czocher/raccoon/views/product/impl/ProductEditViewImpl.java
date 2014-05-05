package org.czocher.raccoon.views.product.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.product.ProductEditPresenter;
import org.czocher.raccoon.views.product.ProductEditView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProductEditViewImpl implements ProductEditView {

	private ProductEditPresenter presenter;
	private Template template;

	public ProductEditViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("product/productEdit.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("product", presenter.getProduct());
		values.put("productEditPath", ProductEditView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ProductEditPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ProductEditPresenter presenter) {
		this.presenter = presenter;
	}

}
