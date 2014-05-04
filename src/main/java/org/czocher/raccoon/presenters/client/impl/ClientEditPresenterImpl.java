package org.czocher.raccoon.presenters.client.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientEditPresenter;
import org.czocher.raccoon.views.client.ClientEditView;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.client.impl.ClientEditViewImpl;

import com.sun.net.httpserver.HttpExchange;

public class ClientEditPresenterImpl implements ClientEditPresenter {

	private Client client;
	private ClientEditView view;

	public ClientEditPresenterImpl(final ClientEditViewImpl clientEditView) {
		setView(clientEditView);
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
	public ClientEditView getView() {
		return view;
	}

	@Override
	public void setView(final ClientEditView view) {
		this.view = view;
		view.setPresenter(this);
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

		if (request.getRequestMethod().equals("POST")) {

			if (!params.containsKey("name") || !params.containsKey("id")) {
				throw new HTTPException(400, "Bad request.");
			}
			c.setName(params.get("name").toString());
			c.saveIt();

			Redirect("/" + ClientView.TAG, request);
		} else {
			RenderViewToResponse(view, request);
		}
	}
}
