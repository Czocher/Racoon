package org.czocher.raccoon.presenters;

import org.czocher.raccoon.models.OrderItem;

public interface OrderItemPresenter extends Presenter {

	OrderItem getOrderItem();

	void setOrderItem(OrderItem order);

}
