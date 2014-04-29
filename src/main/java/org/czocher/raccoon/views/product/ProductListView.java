package org.czocher.raccoon.views.product;

import org.czocher.raccoon.presenters.product.ProductListPresenter;
import org.czocher.raccoon.views.View;

public interface ProductListView extends View {

	static String TAG = "show/products";

	ProductListPresenter getPresenter();

	void setPresenter(ProductListPresenter presenter);

}
