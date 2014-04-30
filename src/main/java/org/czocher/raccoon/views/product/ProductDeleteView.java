package org.czocher.raccoon.views.product;

import org.czocher.raccoon.presenters.product.ProductDeletePresenter;
import org.czocher.raccoon.views.View;

public interface ProductDeleteView extends View {

	static String TAG = "delete/product";

	ProductDeletePresenter getPresenter();

	void setPresenter(ProductDeletePresenter presenter);

}
