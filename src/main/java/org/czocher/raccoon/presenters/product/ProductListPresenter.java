package org.czocher.raccoon.presenters.product;

import java.util.List;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;

public interface ProductListPresenter extends Presenter {

	List<Product> getProductList();

	void setProductList(List<Product> productList);

}
