package org.czocher.raccoon.presenters.order;

import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.Presenter;

public interface OrderPresenter extends Presenter {

	Order getOrder();

	void setOrder(Order order);

}
