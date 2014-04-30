package org.czocher.raccoon.presenters.client.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.client.ClientCreatePresenter;
import org.czocher.raccoon.views.client.ClientCreateView;
import org.czocher.raccoon.views.client.ClientView;

import com.sun.net.httpserver.HttpExchange;

public class ClientCreatePresenterImpl implements ClientCreatePresenter {

	private ClientCreateView view;

	public ClientCreatePresenterImpl(final ClientCreateView clientCreateView) {
		setView(clientCreateView);
	}

	@Override
	public void setView(final ClientCreateView clientCreateView) {
		view = clientCreateView;
		view.setPresenter(this);
	}

	@Override
	public ClientCreateView getView() {
		return view;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (request.getRequestMethod().equals("POST")) {
			if (!params.containsKey("name") || params.get("name") == null || params.get("name").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			final Client n = new Client(params.get("name").toString());
			n.saveIt();

			Redirect("/" + ClientView.TAG + "?id=" + n.getId(), request);
		} else {
			RenderViewToResponse(view, request);
		}
	}

}
