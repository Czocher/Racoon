package org.czocher.raccoon.presenters.product.impl;

import java.util.List;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.product.ProductListPresenter;
import org.czocher.raccoon.views.product.ProductListView;

public class ProductListPresenterImpl implements ProductListPresenter {

	private List<Product> productList;
	private ProductListView view;

	public ProductListPresenterImpl(final ProductListView view, final List<Product> productList) {
		setView(view);
		setProductList(productList);
	}

	@Override
	public String go() throws HTTPException {
		return view.render();
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
	public ProductListView getView() {
		return view;
	}

	@Override
	public void setView(final ProductListView view) {
		this.view = view;
		view.setPresenter(this);
	}

}
