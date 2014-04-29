package org.czocher.raccoon.presenters.impl;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.ProductPresenter;
import org.czocher.raccoon.views.ProductView;

public class ProductPresenterImpl implements ProductPresenter {

	private Product product;
	private ProductView view;

	public ProductPresenterImpl(final ProductView productView, final Product product) {
		setProductView(productView);
		setProduct(product);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
	}

	private void setProductView(final ProductView productView) {
		view = productView;
		view.setPresenter(this);
	}

	public ProductView getProductView() {
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
