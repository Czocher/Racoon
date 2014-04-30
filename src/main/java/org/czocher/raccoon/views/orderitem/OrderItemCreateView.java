package org.czocher.raccoon.views.orderitem;

import org.czocher.raccoon.presenters.orderitem.OrderItemCreatePresenter;
import org.czocher.raccoon.views.View;

public interface OrderItemCreateView extends View {

	static String TAG = "create/orderItem";

	OrderItemCreatePresenter getPresenter();

	void setPresenter(OrderItemCreatePresenter presenter);

}
