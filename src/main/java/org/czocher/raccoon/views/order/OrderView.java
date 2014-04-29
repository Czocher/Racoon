package org.czocher.raccoon.views.order;

import org.czocher.raccoon.presenters.order.OrderPresenter;
import org.czocher.raccoon.views.View;

public interface OrderView extends View {

	static String TAG = "show/order";

	OrderPresenter getPresenter();

	void setPresenter(OrderPresenter presenter);

}
