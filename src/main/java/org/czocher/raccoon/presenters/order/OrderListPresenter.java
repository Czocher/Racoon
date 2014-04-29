package org.czocher.raccoon.presenters.order;

import java.util.List;

import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.Presenter;

public interface OrderListPresenter extends Presenter {

	List<Order> getOrderList();

	void setOrderList(List<Order> orderList);

}
