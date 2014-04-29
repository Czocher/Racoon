package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.OrderItemPresenter;

public interface OrderItemView extends View {

	static String TAG = "show/orderItem";

	OrderItemPresenter getPresenter();

	void setPresenter(OrderItemPresenter presenter);

}
