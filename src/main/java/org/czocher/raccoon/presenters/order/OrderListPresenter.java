package org.czocher.raccoon.presenters.order;

import java.util.List;

import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.order.OrderListView;

public interface OrderListPresenter extends Presenter {

	List<Order> getOrderList();

	void setOrderList(List<Order> orderList);

	OrderListView getView();

	void setView(OrderListView view);

}
