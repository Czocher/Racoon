package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.OrderPresenter;

public interface OrderView extends View {

	static String TAG = "show/order";

	OrderPresenter getPresenter();

	void setPresenter(OrderPresenter presenter);

}
