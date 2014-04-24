package org.czocher.raccoon.views.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.ClientListPresenter;
import org.czocher.raccoon.views.ClientListView;

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

		if (presenter.getClientList() != null && !presenter.getClientList().isEmpty()) {
			values.put("clientList", presenter.getClientList());
		}

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
