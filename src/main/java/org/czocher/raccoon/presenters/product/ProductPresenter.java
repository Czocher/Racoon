package org.czocher.raccoon.presenters.product;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;

public interface ProductPresenter extends Presenter {

	Product getProduct();

	void setProduct(Product product);

}
