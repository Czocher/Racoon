package org.czocher.raccoon.presenters.orderitem.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.presenters.orderitem.OrderItemDeletePresenter;
import org.czocher.raccoon.views.orderitem.OrderItemDeleteView;

import com.sun.net.httpserver.HttpExchange;

public class OrderItemDeletePresenterImpl implements OrderItemDeletePresenter {

	private OrderItemDeleteView view;

	public OrderItemDeletePresenterImpl(final OrderItemDeleteView orderItemDeleteView) {
		setView(orderItemDeleteView);
	}

	@Override
	public void setView(final OrderItemDeleteView orderItemDeleteView) {
		view = orderItemDeleteView;
		view.setPresenter(this);
	}

	@Override
	public OrderItemDeleteView getView() {
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

		final OrderItem c = OrderItem.findById(id);

		if (c == null) {
			throw new HTTPException(400, "Bad request.");
		}
		c.delete(true);

		RenderViewToResponse(view, request);
	}

}
