package org.czocher.raccoon.presenters.product;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.product.ProductEditView;

public interface ProductEditPresenter extends Presenter {

	Product getProduct();

	void setProduct(Product product);

	ProductEditView getView();

	void setView(ProductEditView view);

}
