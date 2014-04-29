package org.czocher.raccoon.views.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.ClientPresenter;
import org.czocher.raccoon.views.ClientView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClientViewImpl implements ClientView {

	private ClientPresenter presenter;
	private Template template;

	public ClientViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("client.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		if (presenter.getClient() != null) {
			values.put("client", presenter.getClient());
		} else {
			throw new HTTPException(404, "File not found.");
		}

		// TODO: change to the TAG
		values.put("orderPath", "show/order");

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ClientPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ClientPresenter presenter) {
		this.presenter = presenter;
	}

}
