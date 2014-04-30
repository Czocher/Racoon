package org.czocher.raccoon.presenters.orderitem.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.presenters.orderitem.OrderItemPresenter;
import org.czocher.raccoon.views.orderitem.OrderItemView;

public class OrderItemPresenterImpl implements OrderItemPresenter {

	private OrderItem orderItem;
	private OrderItemView view;

	public OrderItemPresenterImpl(final OrderItemView orderItemView, final OrderItem orderItem) {
		setView(orderItemView);
		setOrderItem(orderItem);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
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

}
