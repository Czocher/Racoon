package org.czocher.raccoon.presenters.order;

import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.order.OrderView;

public interface OrderPresenter extends Presenter {

	Order getOrder();

	void setOrder(Order order);

	OrderView getView();

	void setView(OrderView view);

}
