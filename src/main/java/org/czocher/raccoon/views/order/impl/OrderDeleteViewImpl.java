package org.czocher.raccoon.views.order.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.order.OrderDeletePresenter;
import org.czocher.raccoon.views.order.OrderDeleteView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class OrderDeleteViewImpl implements OrderDeleteView {

	private OrderDeletePresenter presenter;
	private Template template;

	public OrderDeleteViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("elementDelete.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();

		try {
			template.process(null, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public OrderDeletePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final OrderDeletePresenter presenter) {
		this.presenter = presenter;
	}

}
