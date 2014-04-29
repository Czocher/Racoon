package org.czocher.raccoon.views.order;

import org.czocher.raccoon.presenters.order.OrderListPresenter;
import org.czocher.raccoon.views.View;

public interface OrderListView extends View {

	static String TAG = "show/orders";

	OrderListPresenter getPresenter();

	void setPresenter(OrderListPresenter presenter);

}
