package org.czocher.raccoon.presenters.product.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.presenters.product.ProductCreatePresenter;
import org.czocher.raccoon.views.product.ProductCreateView;

public class ProductCreatePresenterImpl implements ProductCreatePresenter {

	private ProductCreateView view;

	public ProductCreatePresenterImpl(final ProductCreateView productCreateView) {
		setProductCreateView(productCreateView);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setProductCreateView(final ProductCreateView productCreateView) {
		view = productCreateView;
		view.setPresenter(this);
	}

	public ProductCreateView getProductCreateView() {
		return view;
	}

}
