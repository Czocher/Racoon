package org.czocher.raccoon.presenters.orderitem;

import java.util.List;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;

public interface OrderItemCreatePresenter extends Presenter {

	List<Product> getProductList();

	void setProductList(List<Product> productList);

	int getOrderId();

	void setOrderId(int orderId);

}
