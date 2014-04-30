package org.czocher.raccoon.views.product.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.product.ProductCreatePresenter;
import org.czocher.raccoon.views.product.ProductCreateView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProductCreateViewImpl implements ProductCreateView {

	private ProductCreatePresenter presenter;
	private Template template;

	public ProductCreateViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("productCreate.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("productCreatePath", ProductCreateView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ProductCreatePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ProductCreatePresenter presenter) {
		this.presenter = presenter;
	}

}
