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
		setView(clientListView);
		setClientList(clientList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public void setView(final ClientListView clientListView) {
		view = clientListView;
		view.setPresenter(this);
	}

	@Override
	public ClientListView getView() {
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
