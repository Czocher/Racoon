package org.czocher.raccoon.presenters.orderitem;

import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.orderitem.OrderItemView;

public interface OrderItemPresenter extends Presenter {

	OrderItem getOrderItem();

	void setOrderItem(OrderItem order);

	OrderItemView getView();

	void setView(OrderItemView view);

}
