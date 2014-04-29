package org.czocher.raccoon.presenters;

import java.util.List;

import org.czocher.raccoon.models.Product;

public interface ProductListPresenter extends Presenter {

	List<Product> getProductList();

	void setProductList(List<Product> productList);

}
