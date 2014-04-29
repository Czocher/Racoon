package org.czocher.raccoon.views.product;

import org.czocher.raccoon.presenters.product.ProductPresenter;
import org.czocher.raccoon.views.View;

public interface ProductView extends View {

	static String TAG = "show/product";

	ProductPresenter getPresenter();

	void setPresenter(ProductPresenter presenter);

}
