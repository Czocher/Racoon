package org.czocher.raccoon.presenters.product.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductPresenter;
import org.czocher.raccoon.views.product.ProductView;

import com.sun.net.httpserver.HttpExchange;

public class ProductPresenterImpl implements ProductPresenter {

	private Product product;
	private ProductView view;

	public ProductPresenterImpl(final ProductView productView) {
		setView(productView);
	}

	@Override
	public void setView(final ProductView productView) {
		view = productView;
		view.setPresenter(this);
	}

	@Override
	public ProductView getView() {
		return view;
	}

	@Override
	public Product getProduct() {
		return product;
	}

	@Override
	public void setProduct(final Product product) {
		this.product = product;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		final Product p = Product.findById(id);
		if (p == null) {
			throw new HTTPException(404, "File not found.");
		}

		setProduct(p);
		RenderViewToResponse(view, request);
	}

}
