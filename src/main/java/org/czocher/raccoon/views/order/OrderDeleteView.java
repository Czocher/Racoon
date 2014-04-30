package org.czocher.raccoon.views.order;

import org.czocher.raccoon.presenters.order.OrderDeletePresenter;
import org.czocher.raccoon.views.View;

public interface OrderDeleteView extends View {

	static String TAG = "delete/order";

	OrderDeletePresenter getPresenter();

	void setPresenter(OrderDeletePresenter orderDeletePresenter);

}
