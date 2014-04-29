package org.czocher.raccoon.views.client.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.client.NewClientPresenter;
import org.czocher.raccoon.views.client.NewClientView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class NewClientViewImpl implements NewClientView {

	private NewClientPresenter presenter;
	private Template template;

	public NewClientViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("newClient.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("newClientPath", NewClientView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public NewClientPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final NewClientPresenter presenter) {
		this.presenter = presenter;
	}

}
