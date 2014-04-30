package org.czocher.raccoon.presenters.product;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.product.ProductCreateView;

public interface ProductCreatePresenter extends Presenter {

	ProductCreateView getView();

	void setView(ProductCreateView view);

}
