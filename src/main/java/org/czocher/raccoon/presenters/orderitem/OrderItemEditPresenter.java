package org.czocher.raccoon.presenters.orderitem;

import java.util.List;

import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.orderitem.OrderItemEditView;

public interface OrderItemEditPresenter extends Presenter {

	List<Product> getProductList();

	void setProductList(List<Product> productList);

	OrderItem getOrderItem();

	void setOrderItem(OrderItem orderItem);

	OrderItemEditView getView();

	void setView(OrderItemEditView view);

}
