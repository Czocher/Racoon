package org.czocher.raccoon.presenters.order.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderPresenter;
import org.czocher.raccoon.views.order.OrderView;

import com.sun.net.httpserver.HttpExchange;

public class OrderPresenterImpl implements OrderPresenter {

	private Order order;
	private OrderView view;

	public OrderPresenterImpl(final OrderView orderView) {
		setView(orderView);
	}

	@Override
	public void setView(final OrderView orderView) {
		view = orderView;
		view.setPresenter(this);
	}

	@Override
	public OrderView getView() {
		return view;
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

		setOrder(o);

		RenderViewToResponse(view, request);
	}

}
