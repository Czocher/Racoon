package org.czocher.raccoon.presenters;

import org.czocher.raccoon.models.Order;

public interface OrderPresenter extends Presenter {

	Order getOrder();

	void setOrder(Order order);

}
