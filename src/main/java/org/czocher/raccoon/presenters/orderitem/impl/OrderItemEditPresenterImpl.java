package org.czocher.raccoon.presenters.orderitem.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.List;
import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.orderitem.OrderItemEditPresenter;
import org.czocher.raccoon.views.orderitem.OrderItemEditView;
import org.czocher.raccoon.views.orderitem.OrderItemView;

import com.sun.net.httpserver.HttpExchange;

public class OrderItemEditPresenterImpl implements OrderItemEditPresenter {

	private OrderItemEditView view;
	private List<Product> productList;
	private OrderItem orderItem;

	public OrderItemEditPresenterImpl(final OrderItemEditView orderItemEditView) {
		setView(orderItemEditView);
	}

	@Override
	public void setView(final OrderItemEditView orderItemEditView) {
		view = orderItemEditView;
		view.setPresenter(this);
	}

	@Override
	public OrderItemEditView getView() {
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
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (request.getRequestMethod().equals("POST")) {
			if (!params.containsKey("id") || params.get("id") == null || params.get("id").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			if (!params.containsKey("quantity") || params.get("quantity") == null || params.get("quantity").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			if (!params.containsKey("productId") || params.get("productId") == null || params.get("productId").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			int id = 0;
			try {
				id = Integer.parseInt(params.get("id").toString());
			} catch (final NumberFormatException | NullPointerException e) {
				throw new HTTPException(400, "Bad request.");
			}

			final OrderItem oi = OrderItem.findById(id);

			if (oi == null) {
				throw new HTTPException(404, "Not found.");
			}

			int quantity = 0;
			try {
				quantity = Integer.parseInt(params.get("quantity").toString());
			} catch (final NumberFormatException | NullPointerException e) {
				throw new HTTPException(400, "Bad request.");
			}

			int productId = 0;
			try {
				productId = Integer.parseInt(params.get("productId").toString());
			} catch (final NumberFormatException | NullPointerException e) {
				throw new HTTPException(400, "Bad request.");
			}

			final Product p = Product.findById(productId);

			if (p == null) {
				throw new HTTPException(404, "Not found.");
			}

			oi.setQuantity(quantity);
			oi.setProduct(p);
			oi.saveIt();

			Redirect("/" + OrderItemView.TAG, request);
		} else {

			if (!params.containsKey("id") || params.get("id") == null || params.get("id").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			int id = 0;
			try {
				id = Integer.parseInt(params.get("id").toString());
			} catch (final NumberFormatException | NullPointerException e) {
				throw new HTTPException(400, "Bad request.");
			}

			final OrderItem oi = OrderItem.findById(id);

			if (oi == null) {
				throw new HTTPException(404, "Not found.");
			}

			setOrderItem(oi);

			final List<Product> productList = Product.findAll();

			if (productList.isEmpty()) {
				throw new HTTPException(400, "There are no clients specified");
			}

			setProductList(productList);
			RenderViewToResponse(view, request);
		}
	}

	@Override
	public OrderItem getOrderItem() {
		return orderItem;
	}

	@Override
	public void setOrderItem(final OrderItem orderItem) {
		this.orderItem = orderItem;
	}
}
