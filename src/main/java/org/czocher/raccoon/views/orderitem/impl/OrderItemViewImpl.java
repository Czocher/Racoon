package org.czocher.raccoon.views.orderitem.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.orderitem.OrderItemPresenter;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.order.OrderView;
import org.czocher.raccoon.views.orderitem.OrderItemView;
import org.czocher.raccoon.views.product.ProductView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderItemViewImpl implements OrderItemView {

	private OrderItemPresenter presenter;
	private Template template;

	public OrderItemViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("orderItem/orderItem.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("orderItem", presenter.getOrderItem());

		values.put("productPath", ProductView.TAG);
		values.put("orderPath", OrderView.TAG);
		values.put("clientPath", ClientView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderItemPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderItemPresenter presenter) {
		this.presenter = presenter;
	}

}
