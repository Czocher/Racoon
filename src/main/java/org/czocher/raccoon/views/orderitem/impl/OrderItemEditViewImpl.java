package org.czocher.raccoon.views.orderitem.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.orderitem.OrderItemEditPresenter;
import org.czocher.raccoon.views.orderitem.OrderItemEditView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderItemEditViewImpl implements OrderItemEditView {

	private OrderItemEditPresenter presenter;
	private Template template;

	public OrderItemEditViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("orderItem/orderItemEdit.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("orderItem", presenter.getOrderItem());
		values.put("productList", presenter.getProductList());
		values.put("orderItemEditPath", OrderItemEditView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderItemEditPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderItemEditPresenter presenter) {
		this.presenter = presenter;
	}

}
