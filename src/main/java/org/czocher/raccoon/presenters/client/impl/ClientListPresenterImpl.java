package org.czocher.raccoon.presenters.client.impl;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientListPresenter;
import org.czocher.raccoon.views.client.ClientListView;

public class ClientListPresenterImpl implements ClientListPresenter {

	private List<Client> clientList;
	private ClientListView view;

	public ClientListPresenterImpl(final ClientListView clientListView, final List<Client> clientList) {
		setClientListView(clientListView);
		setClientList(clientList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setClientListView(final ClientListView clientListView) {
		view = clientListView;
		view.setPresenter(this);
	}

	public ClientListView getClientListView() {
		return view;
	}

	@Override
	public List<Client> getClientList() {
		return clientList;
	}

	@Override
	public void setClientList(final List<Client> clientList) {
		this.clientList = clientList;
	}

}
