package org.czocher.raccoon.presenters.orderitem.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.List;
import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.orderitem.OrderItemCreatePresenter;
import org.czocher.raccoon.views.order.OrderView;
import org.czocher.raccoon.views.orderitem.OrderItemCreateView;

import com.sun.net.httpserver.HttpExchange;

public class OrderItemCreatePresenterImpl implements OrderItemCreatePresenter {

	private OrderItemCreateView view;
	private List<Product> productList;
	private int orderId;

	public OrderItemCreatePresenterImpl(final OrderItemCreateView orderItemCreateView) {
		setView(orderItemCreateView);
	}

	@Override
	public void setView(final OrderItemCreateView orderItemCreateView) {
		view = orderItemCreateView;
		view.setPresenter(this);
	}

	@Override
	public OrderItemCreateView getView() {
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

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (request.getRequestMethod().equals("POST")) {

			if (!params.containsKey("productId") || params.get("productId") == null || params.get("productId").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.1");
			}

			if (!params.containsKey("orderId") || params.get("orderId") == null || params.get("productId").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.2");
			}

			if (!params.containsKey("quantity") || params.get("quantity") == null || params.get("quantity").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.3");
			}

			int productId = 0;
			int orderId = 0;
			int quantity = 0;
			try {
				productId = Integer.parseInt((String) params.get("productId"));
				orderId = Integer.parseInt((String) params.get("orderId"));
				quantity = Integer.parseInt((String) params.get("quantity"));
			} catch (final NumberFormatException e) {
				throw new HTTPException(400, "Bad request.");
			}

			final OrderItem n = new OrderItem(quantity);
			final Order o = Order.findById(orderId);
			final Product p = Product.findById(productId);

			if (o == null || p == null) {
				throw new HTTPException(400, "Bad request.");
			}

			o.addOrderItem(n);
			p.addOrderItem(n);

			Redirect("/" + OrderView.TAG + "?id=" + o.getId(), request);
		} else if (request.getRequestMethod().equals("GET") && params.containsKey("orderId")) {

			int orderId = 0;
			try {
				orderId = Integer.parseInt((String) params.get("orderId"));
			} catch (final NumberFormatException e) {
				throw new HTTPException(400, "Bad request.");
			}

			setOrderId(orderId);
			setProductList(Product.findAll());
			RenderViewToResponse(view, request);
		} else {
			throw new HTTPException(400, "Bad request.");
		}
	}
}
