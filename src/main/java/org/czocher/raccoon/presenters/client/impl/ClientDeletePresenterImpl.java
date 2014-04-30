package org.czocher.raccoon.presenters.client.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientDeletePresenter;
import org.czocher.raccoon.views.client.ClientDeleteView;

import com.sun.net.httpserver.HttpExchange;

public class ClientDeletePresenterImpl implements ClientDeletePresenter {

	private ClientDeleteView view;

	public ClientDeletePresenterImpl(final ClientDeleteView clientDeleteView) {
		setView(clientDeleteView);
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

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (!params.containsKey("id") || params.get("id") == null || params.get("id").toString().isEmpty()) {
			throw new HTTPException(400, "Bad request.");
		}

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(400, "Bad request.");
		}

		final Client c = Client.findById(id);

		if (c == null) {
			throw new HTTPException(400, "Bad request.");
		}
		c.delete(true);

		RenderViewToResponse(view, request);
	}

}
