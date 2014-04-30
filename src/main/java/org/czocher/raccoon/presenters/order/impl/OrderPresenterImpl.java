package org.czocher.raccoon.presenters.order.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderPresenter;
import org.czocher.raccoon.views.order.OrderView;

public class OrderPresenterImpl implements OrderPresenter {

	private Order order;
	private OrderView view;

	public OrderPresenterImpl(final OrderView orderView, final Order order) {
		setView(orderView);
		setOrder(order);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
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

}
