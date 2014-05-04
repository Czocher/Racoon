package org.czocher.raccoon.views.order;

import org.czocher.raccoon.presenters.order.OrderEditPresenter;
import org.czocher.raccoon.views.View;

public interface OrderEditView extends View {

	static String TAG = "edit/order";

	OrderEditPresenter getPresenter();

	void setPresenter(OrderEditPresenter presenter);

}
