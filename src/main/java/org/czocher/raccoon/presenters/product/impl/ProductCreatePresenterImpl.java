package org.czocher.raccoon.presenters.product.impl;

import static org.czocher.raccoon.shortcuts.Shortcuts.Redirect;
import static org.czocher.raccoon.shortcuts.Shortcuts.RenderViewToResponse;

import java.util.Map;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductCreatePresenter;
import org.czocher.raccoon.views.product.ProductCreateView;
import org.czocher.raccoon.views.product.ProductView;

import com.sun.net.httpserver.HttpExchange;

public class ProductCreatePresenterImpl implements ProductCreatePresenter {

	private ProductCreateView view;

	public ProductCreatePresenterImpl(final ProductCreateView productCreateView) {
		setView(productCreateView);
	}

	@Override
	public void setView(final ProductCreateView productCreateView) {
		view = productCreateView;
		view.setPresenter(this);
	}

	@Override
	public ProductCreateView getView() {
		return view;
	}

	@Override
	public void go(final HttpExchange request) throws HTTPException {
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");

		if (request.getRequestMethod().equals("POST")) {
			if (!params.containsKey("name") || params.get("name") == null || params.get("name").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			final Product p = new Product(params.get("name").toString());
			p.saveIt();

			Redirect("/" + ProductView.TAG + "?id=" + p.getId(), request);
		} else {

			RenderViewToResponse(view, request);
		}
	}

}
