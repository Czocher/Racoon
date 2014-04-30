package org.czocher.raccoon.presenters.order;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.order.OrderDeleteView;

public interface OrderDeletePresenter extends Presenter {

	OrderDeleteView getView();

	void setView(OrderDeleteView view);

}
