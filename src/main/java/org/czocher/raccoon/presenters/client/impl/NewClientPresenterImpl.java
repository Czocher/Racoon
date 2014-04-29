package org.czocher.raccoon.presenters.client.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.client.NewClientPresenter;
import org.czocher.raccoon.views.client.NewClientView;

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
