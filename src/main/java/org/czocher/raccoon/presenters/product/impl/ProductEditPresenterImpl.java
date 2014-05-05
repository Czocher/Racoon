package org.czocher.raccoon.presenters.product.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductEditPresenter;
import org.czocher.raccoon.views.product.ProductEditView;
import org.czocher.raccoon.views.product.ProductView;

import com.sun.net.httpserver.HttpExchange;

public class ProductEditPresenterImpl implements ProductEditPresenter {

	private Product product;
	private ProductEditView view;

	public ProductEditPresenterImpl(final ProductEditView productEditView) {
		setView(productEditView);
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
	public ProductEditView getView() {
		return view;
	}

	@Override
	public void setView(final ProductEditView view) {
		this.view = view;
		view.setPresenter(this);
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		int id = 0;
		try {
			id = Integer.parseInt(params.get("id").toString());
		} catch (final NumberFormatException | NullPointerException e) {
			throw new HTTPException(404, "File not found.");
		}

		final Product c = Product.findById(id);
		if (c == null) {
			throw new HTTPException(404, "File not found.");
		}

		setProduct(c);

		if (request.getRequestMethod().equals("POST")) {

			if (!params.containsKey("name") || !params.containsKey("id")) {
				throw new HTTPException(400, "Bad request.");
			}
			c.setName(params.get("name").toString());
			c.saveIt();

			Redirect("/" + ProductView.TAG, request);
		} else {
			RenderViewToResponse(view, request);
		}
	}
}
