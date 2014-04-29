package org.czocher.raccoon.views.client.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.client.ClientListPresenter;
import org.czocher.raccoon.views.client.ClientListView;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.client.NewClientView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClientListViewImpl implements ClientListView {

	private ClientListPresenter presenter;
	private Template template;

	public ClientListViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("clientList.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("clientPath", ClientView.TAG);
		values.put("newClientPath", NewClientView.TAG);
		values.put("clientList", presenter.getClientList());

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ClientListPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ClientListPresenter presenter) {
		this.presenter = presenter;
	}

}
