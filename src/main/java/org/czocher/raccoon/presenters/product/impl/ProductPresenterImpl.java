package org.czocher.raccoon.presenters.product.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductPresenter;
import org.czocher.raccoon.views.product.ProductView;

public class ProductPresenterImpl implements ProductPresenter {

	private Product product;
	private ProductView view;

	public ProductPresenterImpl(final ProductView productView, final Product product) {
		setView(productView);
		setProduct(product);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
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

}
