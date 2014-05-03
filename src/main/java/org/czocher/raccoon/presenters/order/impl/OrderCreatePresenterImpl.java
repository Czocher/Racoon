package org.czocher.raccoon.presenters.order.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.List;
import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderCreatePresenter;
import org.czocher.raccoon.views.order.OrderCreateView;
import org.czocher.raccoon.views.order.OrderView;

import com.sun.net.httpserver.HttpExchange;

public class OrderCreatePresenterImpl implements OrderCreatePresenter {

	private OrderCreateView view;
	private List<Client> clientList;

	public OrderCreatePresenterImpl(final OrderCreateView orderCreateView) {
		setView(orderCreateView);
	}

	@Override
	public void setView(final OrderCreateView orderCreateView) {
		view = orderCreateView;
		view.setPresenter(this);
	}

	@Override
	public OrderCreateView getView() {
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

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (request.getRequestMethod().equals("POST") || request.getRequestMethod().equals("GET") && params.containsKey("clientId")) {
			if (!params.containsKey("clientId") || params.get("clientId") == null || params.get("clientId").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			int id = 0;
			try {
				id = Integer.parseInt(params.get("clientId").toString());
			} catch (final NumberFormatException e) {
				throw new HTTPException(400, "Bad request.");
			}

			final Order o = new Order();
			final Client c = Client.findById(id);

			if (c == null) {
				throw new HTTPException(400, "Bad request.");
			}

			c.addOrder(o);

			Redirect("/" + OrderView.TAG + "?id=" + o.getId(), request);
		} else {
			final List<Client> c = Client.findAll();
			if (c.isEmpty()) {
				throw new HTTPException(400, "There are no clients specified");
			}

			setClientList(c);
			RenderViewToResponse(view, request);
		}

	}

}
