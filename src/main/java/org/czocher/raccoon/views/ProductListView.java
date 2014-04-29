package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.ProductListPresenter;

public interface ProductListView extends View {

	static String TAG = "show/products";

	ProductListPresenter getPresenter();

	void setPresenter(ProductListPresenter presenter);

}
