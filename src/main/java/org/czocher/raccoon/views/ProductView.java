package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.ProductPresenter;

public interface ProductView extends View {

	static String TAG = "show/product";

	ProductPresenter getPresenter();

	void setPresenter(ProductPresenter presenter);

}
