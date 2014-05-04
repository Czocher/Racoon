package org.czocher.raccoon.presenters.order.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.List;
import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderEditPresenter;
import org.czocher.raccoon.views.order.OrderEditView;
import org.czocher.raccoon.views.order.OrderView;

import com.sun.net.httpserver.HttpExchange;

public class OrderEditPresenterImpl implements OrderEditPresenter {

	private Order order;
	private OrderEditView view;
	private List<Client> clientList;

	public OrderEditPresenterImpl(final OrderEditView orderEditView) {
		setView(orderEditView);
	}

	@Override
	public Order getOrder() {
		return order;
	}

	@Override
	public void setOrder(final Order order) {
		this.order = order;
	}

	@Override
	public OrderEditView getView() {
		return view;
	}

	@Override
	public void setView(final OrderEditView view) {
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
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		final Order o = Order.findById(id);
		if (o == null) {
			throw new HTTPException(404, "File not found.");
		}

		setClientList(Client.findAll());
		setOrder(o);

		if (request.getRequestMethod().equals("POST")) {

			if (!params.containsKey("clientId") || !params.containsKey("id")) {
				throw new HTTPException(400, "Bad request.");
			}

			int clientId = 0;
			try {
				clientId = Integer.parseInt(params.get("clientId").toString());
			} catch (final NumberFormatException e) {
				throw new HTTPException(404, "File not found.");
			}

			final Client c = Client.findById(clientId);
			if (c == null) {
				throw new HTTPException(404, "File not found.");
			}

			o.setClient(c);
			o.saveIt();

			Redirect("/" + OrderView.TAG, request);
		} else {
			RenderViewToResponse(view, request);
		}
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
