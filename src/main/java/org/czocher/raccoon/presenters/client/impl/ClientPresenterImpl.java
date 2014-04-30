package org.czocher.raccoon.presenters.client.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientPresenter;
import org.czocher.raccoon.views.client.ClientView;

public class ClientPresenterImpl implements ClientPresenter {

	private Client client;
	private ClientView view;

	public ClientPresenterImpl(final ClientView clientView, final Client client) {
		setView(clientView);
		setClient(client);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public void setView(final ClientView clientView) {
		view = clientView;
		view.setPresenter(this);
	}

	@Override
	public ClientView getView() {
		return view;
	}

	@Override
	public Client getClient() {
		return client;
	}

	@Override
	public void setClient(final Client client) {
		this.client = client;
	}

}
