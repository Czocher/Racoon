package org.czocher.raccoon.presenters.index.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.index.IndexPresenter;
import org.czocher.raccoon.views.index.IndexView;

import com.sun.net.httpserver.HttpExchange;

public class IndexPresenterImpl implements IndexPresenter {

	private IndexView view;

	public IndexPresenterImpl(final IndexView view) {
		this.view = view;
		this.view.setPresenter(this);
	}

	@Override
	public IndexView getView() {
		return view;
	}

	@Override
	public void setView(final IndexView view) {
		this.view = view;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		RenderViewToResponse(view, request);
	}
}
