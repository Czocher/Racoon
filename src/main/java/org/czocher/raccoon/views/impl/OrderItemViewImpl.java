package org.czocher.raccoon.views.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.OrderItemPresenter;
import org.czocher.raccoon.views.ClientView;
import org.czocher.raccoon.views.OrderItemView;
import org.czocher.raccoon.views.OrderView;
import org.czocher.raccoon.views.ProductView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderItemViewImpl implements OrderItemView {

	private OrderItemPresenter presenter;
	private Template template;

	public OrderItemViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("orderItem.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		if (presenter.getOrderItem() != null) {
			values.put("orderItem", presenter.getOrderItem());
		} else {
			throw new HTTPException(404, "File not found.");
		}

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
