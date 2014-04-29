package org.czocher.raccoon.views.index.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.index.IndexPresenter;
import org.czocher.raccoon.views.client.ClientListView;
import org.czocher.raccoon.views.order.OrderListView;
import org.czocher.raccoon.views.product.ProductListView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class IndexViewImpl implements org.czocher.raccoon.views.index.IndexView {

	private IndexPresenter presenter;
	private Template template;

	public IndexViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("index.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("clientListPath", ClientListView.TAG);
		values.put("orderListPath", OrderListView.TAG);
		values.put("productListPath", ProductListView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public IndexPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final IndexPresenter presenter) {
		this.presenter = presenter;
	}

}
