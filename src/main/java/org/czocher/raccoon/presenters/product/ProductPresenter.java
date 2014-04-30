package org.czocher.raccoon.presenters.product;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.product.ProductView;

public interface ProductPresenter extends Presenter {

	Product getProduct();

	void setProduct(Product product);

	ProductView getView();

	void setView(ProductView view);

}
