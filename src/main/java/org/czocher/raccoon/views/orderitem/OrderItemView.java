package org.czocher.raccoon.views.orderitem;

import org.czocher.raccoon.presenters.orderitem.OrderItemPresenter;
import org.czocher.raccoon.views.View;

public interface OrderItemView extends View {

	static String TAG = "show/orderItem";

	OrderItemPresenter getPresenter();

	void setPresenter(OrderItemPresenter presenter);

}
