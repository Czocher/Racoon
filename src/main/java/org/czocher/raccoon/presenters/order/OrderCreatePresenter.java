package org.czocher.raccoon.presenters.order;

import java.util.List;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.order.OrderCreateView;

public interface OrderCreatePresenter extends Presenter {

	List<Client> getClientList();

	void setClientList(List<Client> clientList);

	OrderCreateView getView();

	void setView(OrderCreateView view);

}
