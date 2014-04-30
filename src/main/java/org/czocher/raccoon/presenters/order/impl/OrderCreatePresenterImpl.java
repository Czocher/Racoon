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
		setView(orderCreateView);
		setClientList(clientList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public void setView(final OrderCreateView orderCreateView) {
		view = orderCreateView;
		view.setPresenter(this);
	}

	@Override
	public OrderCreateView getView() {
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
