package org.czocher.raccoon.views.product.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.product.ProductDeletePresenter;
import org.czocher.raccoon.views.product.ProductDeleteView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProductDeleteViewImpl implements ProductDeleteView {

	private ProductDeletePresenter presenter;
	private Template template;

	public ProductDeleteViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("elementDelete.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();

		try {
			template.process(null, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ProductDeletePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ProductDeletePresenter presenter) {
		this.presenter = presenter;
	}

}
