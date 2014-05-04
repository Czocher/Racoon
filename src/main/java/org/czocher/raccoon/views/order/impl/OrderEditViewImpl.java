package org.czocher.raccoon.views.order.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.order.OrderEditPresenter;
import org.czocher.raccoon.views.order.OrderEditView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderEditViewImpl implements OrderEditView {

	private OrderEditPresenter presenter;
	private Template template;

	public OrderEditViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("order/orderEdit.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("order", presenter.getOrder());
		values.put("clientList", presenter.getClientList());
		values.put("orderEditPath", OrderEditView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderEditPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderEditPresenter presenter) {
		this.presenter = presenter;
	}

}
