package org.czocher.raccoon.presenters.order.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.order.OrderDeletePresenter;
import org.czocher.raccoon.views.order.OrderDeleteView;

public class OrderDeletePresenterImpl implements OrderDeletePresenter {

	private OrderDeleteView view;

	public OrderDeletePresenterImpl(final OrderDeleteView orderDeleteView) {
		setView(orderDeleteView);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	@Override
	public void setView(final OrderDeleteView orderDeleteView) {
		view = orderDeleteView;
		view.setPresenter(this);
	}

	@Override
	public OrderDeleteView getView() {
		return view;
	}

}
