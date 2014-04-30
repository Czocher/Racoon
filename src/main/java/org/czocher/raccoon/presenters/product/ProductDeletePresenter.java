package org.czocher.raccoon.presenters.product;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.product.ProductDeleteView;

public interface ProductDeletePresenter extends Presenter {

	ProductDeleteView getView();

	void setView(ProductDeleteView view);

}
