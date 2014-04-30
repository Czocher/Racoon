package org.czocher.raccoon.presenters.client.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.client.ClientDeletePresenter;
import org.czocher.raccoon.views.client.ClientDeleteView;

public class ClientDeletePresenterImpl implements ClientDeletePresenter {

	private ClientDeleteView view;

	public ClientDeletePresenterImpl(final ClientDeleteView clientDeleteView) {
		setView(clientDeleteView);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public void setView(final ClientDeleteView clientDeleteView) {
		view = clientDeleteView;
		view.setPresenter(this);
	}

	@Override
	public ClientDeleteView getView() {
		return view;
	}

}
