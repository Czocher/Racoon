package org.czocher.raccoon.views.client.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.client.ClientDeletePresenter;
import org.czocher.raccoon.views.client.ClientDeleteView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClientDeleteViewImpl implements ClientDeleteView {

	private ClientDeletePresenter presenter;
	private Template template;

	public ClientDeleteViewImpl() {
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
	public ClientDeletePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ClientDeletePresenter presenter) {
		this.presenter = presenter;
	}

}
