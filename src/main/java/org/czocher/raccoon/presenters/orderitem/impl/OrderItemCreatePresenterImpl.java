package org.czocher.raccoon.presenters.orderitem.impl;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.orderitem.OrderItemCreatePresenter;
import org.czocher.raccoon.views.orderitem.OrderItemCreateView;

public class OrderItemCreatePresenterImpl implements OrderItemCreatePresenter {

	private OrderItemCreateView view;
	private List<Product> productList;
	private int orderId;

	public OrderItemCreatePresenterImpl(final OrderItemCreateView orderItemCreateView, final int orderId, final List<Product> productList) {
		setOrderItemCreateView(orderItemCreateView);
		setOrderId(orderId);
		setProductList(productList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setOrderItemCreateView(final OrderItemCreateView orderItemCreateView) {
		view = orderItemCreateView;
		view.setPresenter(this);
	}

	public OrderItemCreateView getOrderItemCreateView() {
		return view;
	}

	@Override
	public List<Product> getProductList() {
		return productList;
	}

	@Override
	public void setProductList(final List<Product> productList) {
		this.productList = productList;
	}

	@Override
	public int getOrderId() {
		return orderId;
	}

	@Override
	public void setOrderId(final int orderId) {
		this.orderId = orderId;
	}

}
