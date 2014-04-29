package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.OrderListPresenter;

public interface OrderListView extends View {

	static String TAG = "show/orders";

	OrderListPresenter getPresenter();

	void setPresenter(OrderListPresenter presenter);

}
