package org.czocher.raccoon.views.impl;

import java.io.IOException;

import org.czocher.raccoon.AppDriver;
import org.czocher.raccoon.presenters.IndexPresenter;
import org.czocher.raccoon.views.IndexView;

import freemarker.template.Template;

public class IndexViewImpl implements IndexView {

	private IndexPresenter presenter;

	@Override
	public String render() {
		Template template = null;
		try {
			template = AppDriver.TEMPL.getTemplate("index.template.ftl");
		} catch (final IOException e) {
			e.printStackTrace();
		}
		return template.toString();
	}

	@Override
	public IndexPresenter getPresenter() {
		return presenter;
	}

	@Override
	public void setPresenter(final IndexPresenter presenter) {
		this.presenter = presenter;
	}

}
