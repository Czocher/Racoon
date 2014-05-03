package org.czocher.raccoon.views.client.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.client.ClientEditPresenter;
import org.czocher.raccoon.views.client.ClientEditView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClientEditViewImpl implements ClientEditView {

	private ClientEditPresenter presenter;
	private Template template;

	public ClientEditViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("client/clientEdit.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("client", presenter.getClient());
		values.put("clientEditPath", ClientEditView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ClientEditPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ClientEditPresenter presenter) {
		this.presenter = presenter;
	}

}
