package org.czocher.raccoon.presenters.orderitem;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.orderitem.OrderItemDeleteView;

public interface OrderItemDeletePresenter extends Presenter {

	OrderItemDeleteView getView();

	void setView(OrderItemDeleteView view);

}
