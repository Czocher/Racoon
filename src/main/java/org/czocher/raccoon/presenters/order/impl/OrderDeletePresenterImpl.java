package org.czocher.raccoon.presenters.order.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderDeletePresenter;
import org.czocher.raccoon.views.order.OrderDeleteView;

import com.sun.net.httpserver.HttpExchange;

public class OrderDeletePresenterImpl implements OrderDeletePresenter {

	private OrderDeleteView view;

	public OrderDeletePresenterImpl(final OrderDeleteView orderDeleteView) {
		setView(orderDeleteView);
	}

	@Override
	public void setView(final OrderDeleteView orderDeleteView) {
		view = orderDeleteView;
		view.setPresenter(this);
	}

	@Override
	public OrderDeleteView getView() {
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

		final Order o = Order.findById(id);

		if (o == null) {
			throw new HTTPException(400, "Bad request.");
		} else {
			o.delete(true);
			RenderViewToResponse(view, request);
		}
	}
}
