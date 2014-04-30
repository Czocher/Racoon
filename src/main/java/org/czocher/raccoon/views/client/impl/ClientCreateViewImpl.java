package org.czocher.raccoon.views.client.impl;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.client.ClientCreatePresenter;
import org.czocher.raccoon.views.client.ClientCreateView;

import freemarker.template.Template;
import freemarker.template.TemplateException;

public class ClientCreateViewImpl implements ClientCreateView {

	private ClientCreatePresenter presenter;
	private Template template;

	public ClientCreateViewImpl() {
		try {
			template = AppDriver.TEMPL.getTemplate("client/clientCreate.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public String render() throws HTTPException {
		final Writer out = new StringWriter();
		final Map<String, Object> values = new HashMap<>();

		values.put("clientCreatePath", ClientCreateView.TAG);

		try {
			template.process(values, out);
		} catch (TemplateException | IOException e) {
			e.printStackTrace();
		}

		return out.toString();
	}

	@Override
	public ClientCreatePresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final ClientCreatePresenter presenter) {
		this.presenter = presenter;
	}

}
