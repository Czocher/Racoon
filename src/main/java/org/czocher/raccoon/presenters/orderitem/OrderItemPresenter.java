package org.czocher.raccoon.presenters.orderitem;

import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.presenters.Presenter;

public interface OrderItemPresenter extends Presenter {

	OrderItem getOrderItem();

	void setOrderItem(OrderItem order);

}
