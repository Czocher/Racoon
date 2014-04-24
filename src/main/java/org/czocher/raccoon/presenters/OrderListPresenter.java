package org.czocher.raccoon.presenters;

import java.util.List;

import org.czocher.raccoon.models.Order;

public interface OrderListPresenter extends Presenter {

	List<Order> getOrderList();

	void setOrderList(List<Order> orderList);

}
