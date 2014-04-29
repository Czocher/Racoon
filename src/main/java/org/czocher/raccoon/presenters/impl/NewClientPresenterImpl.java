package org.czocher.raccoon.presenters.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.NewClientPresenter;
import org.czocher.raccoon.views.NewClientView;

public class NewClientPresenterImpl implements NewClientPresenter {

	private NewClientView view;

	public NewClientPresenterImpl(final NewClientView newClientView) {
		setNewClientView(newClientView);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setNewClientView(final NewClientView newClientView) {
		view = newClientView;
		view.setPresenter(this);
	}

	public NewClientView getNewClientView() {
		return view;
	}

}
