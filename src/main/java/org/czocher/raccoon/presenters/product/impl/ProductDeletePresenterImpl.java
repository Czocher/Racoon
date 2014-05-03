package org.czocher.raccoon.presenters.product.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductDeletePresenter;
import org.czocher.raccoon.views.product.ProductDeleteView;

import com.sun.net.httpserver.HttpExchange;

public class ProductDeletePresenterImpl implements ProductDeletePresenter {

	private ProductDeleteView view;

	public ProductDeletePresenterImpl(final ProductDeleteView productDeleteView) {
		setView(productDeleteView);
	}

	@Override
	public void setView(final ProductDeleteView productDeleteView) {
		view = productDeleteView;
		view.setPresenter(this);
	}

	@Override
	public ProductDeleteView getView() {
		return view;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (!params.containsKey("id") || params.get("id") == null || params.get("id").toString().isEmpty()) {
			throw new HTTPException(400, "Bad request.");
		}

		int id = 0;
		try {
			id = Integer.parseInt(params.get("id").toString());
		} catch (final NumberFormatException e) {
			throw new HTTPException(400, "Bad request.");
		}

		final Product c = Product.findById(id);

		if (c == null) {
			throw new HTTPException(400, "Bad request.");
		}
		c.delete(true);

		RenderViewToResponse(view, request);
	}

}
