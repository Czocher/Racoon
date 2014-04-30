package org.czocher.raccoon.views.orderitem.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.orderitem.OrderItemCreatePresenter;
import org.czocher.raccoon.views.orderitem.OrderItemCreateView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderItemCreateViewImpl implements OrderItemCreateView {

	private OrderItemCreatePresenter presenter;
	private Template template;

	public OrderItemCreateViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("orderItem/orderItemCreate.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("orderItemCreatePath", OrderItemCreateView.TAG);
		values.put("productList", presenter.getProductList());
		values.put("orderId", presenter.getOrderId());

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderItemCreatePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderItemCreatePresenter presenter) {
		this.presenter = presenter;
	}

}
