package org.czocher.raccoon.presenters.orderitem.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.presenters.orderitem.OrderItemPresenter;
import org.czocher.raccoon.views.orderitem.OrderItemView;

import com.sun.net.httpserver.HttpExchange;

public class OrderItemPresenterImpl implements OrderItemPresenter {

	private OrderItem orderItem;
	private OrderItemView view;

	public OrderItemPresenterImpl(final OrderItemView orderItemView) {
		setView(orderItemView);
	}

	@Override
	public void setView(final OrderItemView orderItemView) {
		view = orderItemView;
		view.setPresenter(this);
	}

	@Override
	public OrderItemView getView() {
		return view;
	}

	@Override
	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	public void setOrderItem(final OrderItem orderItem) {
		this.orderItem = orderItem;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		final OrderItem oi = OrderItem.findById(id);

		if (oi == null) {
			throw new HTTPException(404, "File not found.");
		}

		setOrderItem(oi);

		RenderViewToResponse(view, request);
	}

}
