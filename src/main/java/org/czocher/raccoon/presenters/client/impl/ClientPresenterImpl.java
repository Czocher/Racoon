package org.czocher.raccoon.presenters.client.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientPresenter;
import org.czocher.raccoon.views.client.ClientView;

import com.sun.net.httpserver.HttpExchange;

public class ClientPresenterImpl implements ClientPresenter {

	private Client client;
	private ClientView view;

	public ClientPresenterImpl(final ClientView clientView) {
		setView(clientView);
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

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		int id = 0;
		try {
			id = Integer.parseInt(params.get("id").toString());
		} catch (final NumberFormatException | NullPointerException e) {
			throw new HTTPException(404, "File not found.");
		}

		final Client c = Client.findById(id);
		if (c == null) {
			throw new HTTPException(404, "File not found.");
		}

		setClient(c);

		RenderViewToResponse(view, request);
	}
}
