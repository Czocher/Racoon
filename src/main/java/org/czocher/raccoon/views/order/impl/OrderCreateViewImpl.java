package org.czocher.raccoon.views.order.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.order.OrderCreatePresenter;
import org.czocher.raccoon.views.order.OrderCreateView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderCreateViewImpl implements OrderCreateView {

	private OrderCreatePresenter presenter;
	private Template template;

	public OrderCreateViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("order/orderCreate.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("clientList", presenter.getClientList());
		values.put("orderCreatePath", OrderCreateView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderCreatePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderCreatePresenter presenter) {
		this.presenter = presenter;
	}

}
