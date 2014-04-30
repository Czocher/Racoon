package org.czocher.raccoon.views.orderitem.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.orderitem.OrderItemDeletePresenter;
import org.czocher.raccoon.views.orderitem.OrderItemDeleteView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderItemDeleteViewImpl implements OrderItemDeleteView {

	private OrderItemDeletePresenter presenter;
	private Template template;

	public OrderItemDeleteViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("elementDelete.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();

		try {
			template.process(null, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderItemDeletePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderItemDeletePresenter presenter) {
		this.presenter = presenter;
	}

}
