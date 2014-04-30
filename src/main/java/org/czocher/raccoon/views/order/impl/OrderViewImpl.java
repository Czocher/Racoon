package org.czocher.raccoon.views.order.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.order.OrderPresenter;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.order.OrderView;
import org.czocher.raccoon.views.orderitem.OrderItemCreateView;
import org.czocher.raccoon.views.orderitem.OrderItemView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderViewImpl implements OrderView {

	private OrderPresenter presenter;
	private Template template;

	public OrderViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("order/order.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		if (presenter.getOrder() != null) {
			values.put("order", presenter.getOrder());
		} else {
			throw new HTTPException(404, "File not found.");
		}

		values.put("orderItemPath", OrderItemView.TAG);
		values.put("orderItemCreatePath", OrderItemCreateView.TAG);
		values.put("clientPath", ClientView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderPresenter presenter) {
		this.presenter = presenter;
	}

}
