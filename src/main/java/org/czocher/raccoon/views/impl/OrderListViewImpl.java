package org.czocher.raccoon.views.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.OrderListPresenter;
import org.czocher.raccoon.views.OrderListView;
import org.czocher.raccoon.views.OrderView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderListViewImpl implements OrderListView {

	private OrderListPresenter presenter;
	private Template template;

	public OrderListViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("orderList.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("orderPath", OrderView.TAG);
		values.put("orderList", presenter.getOrderList());

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderListPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderListPresenter presenter) {
		this.presenter = presenter;
	}

}
