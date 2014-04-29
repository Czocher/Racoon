package org.czocher.raccoon.presenters.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.OrderPresenter;
import org.czocher.raccoon.views.OrderView;

public class OrderPresenterImpl implements OrderPresenter {

	private Order order;
	private OrderView view;

	public OrderPresenterImpl(final OrderView orderView, final Order order) {
		setOrderView(orderView);
		setOrder(order);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setOrderView(final OrderView orderView) {
		view = orderView;
		view.setPresenter(this);
	}

	public OrderView getOrderView() {
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
