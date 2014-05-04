package org.czocher.raccoon.presenters.order;

import java.util.List;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.order.OrderEditView;

public interface OrderEditPresenter extends Presenter {

	OrderEditView getView();

	void setView(OrderEditView view);

	Order getOrder();

	void setOrder(Order order);

	List<Client> getClientList();

	void setClientList(List<Client> clientList);

}
