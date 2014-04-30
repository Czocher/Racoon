package org.czocher.raccoon.presenters.client.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.client.ClientCreatePresenter;
import org.czocher.raccoon.views.client.ClientCreateView;

public class ClientCreatePresenterImpl implements ClientCreatePresenter {

	private ClientCreateView view;

	public ClientCreatePresenterImpl(final ClientCreateView clientCreateView) {
		setClientCreateView(clientCreateView);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setClientCreateView(final ClientCreateView clientCreateView) {
		view = clientCreateView;
		view.setPresenter(this);
	}

	public ClientCreateView getClientCreateView() {
		return view;
	}

}
