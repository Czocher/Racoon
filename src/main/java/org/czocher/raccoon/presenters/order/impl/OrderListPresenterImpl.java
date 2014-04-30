package org.czocher.raccoon.presenters.order.impl;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderListPresenter;
import org.czocher.raccoon.views.order.OrderListView;

public class OrderListPresenterImpl implements OrderListPresenter {

	private List<Order> orderList;
	private OrderListView view;

	public OrderListPresenterImpl(final OrderListView view, final List<Order> orderList) {
		setView(view);
		setOrderList(orderList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public List<Order> getOrderList() {
		return orderList;
	}

	@Override
	public void setOrderList(final List<Order> orderList) {
		this.orderList = orderList;
	}

	@Override
	public OrderListView getView() {
		return view;
	}

	@Override
	public void setView(final OrderListView view) {
		this.view = view;
		view.setPresenter(this);
	}

}
