package org.czocher.raccoon.views.orderitem;

import org.czocher.raccoon.presenters.orderitem.OrderItemEditPresenter;
import org.czocher.raccoon.views.View;

public interface OrderItemEditView extends View {

	static String TAG = "edit/orderItem";

	OrderItemEditPresenter getPresenter();

	void setPresenter(OrderItemEditPresenter presenter);

}
