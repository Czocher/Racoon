package org.czocher.raccoon.views.orderitem;

import org.czocher.raccoon.presenters.orderitem.OrderItemDeletePresenter;
import org.czocher.raccoon.views.View;

public interface OrderItemDeleteView extends View {

	static String TAG = "delete/orderItem";

	OrderItemDeletePresenter getPresenter();

	void setPresenter(OrderItemDeletePresenter presenter);

}
