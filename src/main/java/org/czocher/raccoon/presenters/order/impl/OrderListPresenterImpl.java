package org.czocher.raccoon.presenters.order.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.order.OrderListPresenter;
import org.czocher.raccoon.views.order.OrderListView;

import com.sun.net.httpserver.HttpExchange;

public class OrderListPresenterImpl implements OrderListPresenter {

	private List<Order> orderList;
	private OrderListView view;

	public OrderListPresenterImpl(final OrderListView view) {
		setView(view);
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

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		setOrderList(Order.findAll());
		RenderViewToResponse(view, request);
	}

}
