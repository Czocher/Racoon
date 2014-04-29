package org.czocher.raccoon.presenters.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.IndexPresenter;
import org.czocher.raccoon.views.IndexView;

public class IndexPresenterImpl implements IndexPresenter {

	private IndexView view;

	public IndexPresenterImpl(final IndexView view) {
		this.view = view;
		this.view.setPresenter(this);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public IndexView getView() {
		return view;
	}

	@Override
	public void setView(final IndexView view) {
		this.view = view;
	}

}
