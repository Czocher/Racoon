package org.czocher.raccoon.presenters.order.impl;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.order.OrderCreatePresenter;
import org.czocher.raccoon.views.order.OrderCreateView;

public class OrderCreatePresenterImpl implements OrderCreatePresenter {

	private OrderCreateView view;
	private List<Client> clientList;

	public OrderCreatePresenterImpl(final OrderCreateView orderCreateView, final List<Client> clientList) {
		setOrderCreateView(orderCreateView);
		setClientList(clientList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setOrderCreateView(final OrderCreateView orderCreateView) {
		view = orderCreateView;
		view.setPresenter(this);
	}

	public OrderCreateView getOrderCreateView() {
		return view;
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
