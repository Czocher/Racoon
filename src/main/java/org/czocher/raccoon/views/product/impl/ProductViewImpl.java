package org.czocher.raccoon.views.product.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.product.ProductPresenter;
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
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		if (presenter.getProduct() != null) {
			values.put("product", presenter.getProduct());
		} else {
			throw new HTTPException(404, "File not found.");
		}

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
