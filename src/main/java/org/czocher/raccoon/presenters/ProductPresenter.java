package org.czocher.raccoon.presenters;

import org.czocher.raccoon.models.Product;

public interface ProductPresenter extends Presenter {

	Product getProduct();

	void setProduct(Product product);

}
