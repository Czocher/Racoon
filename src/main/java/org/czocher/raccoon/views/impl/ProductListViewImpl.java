package org.czocher.raccoon.views.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.ProductListPresenter;
import org.czocher.raccoon.views.ProductListView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ProductListViewImpl implements ProductListView {

	private ProductListPresenter presenter;
	private Template template;

	public ProductListViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("productList.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		// TODO: uncomment when the need arises
		// values.put("productPath", ProductView.TAG);
		values.put("productPath", "show/product");
		values.put("productList", presenter.getProductList());

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ProductListPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ProductListPresenter presenter) {
		this.presenter = presenter;
	}

}
