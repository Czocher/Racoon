package org.czocher.raccoon.views.product;

import org.czocher.raccoon.presenters.product.ProductCreatePresenter;
import org.czocher.raccoon.views.View;

public interface ProductCreateView extends View {

	static String TAG = "create/product";

	ProductCreatePresenter getPresenter();

	void setPresenter(ProductCreatePresenter presenter);

}
