package org.czocher.raccoon.views.order;

import org.czocher.raccoon.presenters.order.OrderCreatePresenter;
import org.czocher.raccoon.views.View;

public interface OrderCreateView extends View {

	static String TAG = "create/order";

	OrderCreatePresenter getPresenter();

	void setPresenter(OrderCreatePresenter presenter);

}
