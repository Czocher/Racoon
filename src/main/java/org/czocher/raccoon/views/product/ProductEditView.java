package org.czocher.raccoon.views.product;

import org.czocher.raccoon.presenters.product.ProductEditPresenter;
import org.czocher.raccoon.views.View;

public interface ProductEditView extends View {

	static String TAG = "edit/product";

	ProductEditPresenter getPresenter();

	void setPresenter(ProductEditPresenter presenter);

}
