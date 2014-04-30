package org.czocher.raccoon.presenters.product;

import java.util.List;

import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.product.ProductListView;

public interface ProductListPresenter extends Presenter {

	List<Product> getProductList();

	void setProductList(List<Product> productList);

	ProductListView getView();

	void setView(ProductListView view);

}
