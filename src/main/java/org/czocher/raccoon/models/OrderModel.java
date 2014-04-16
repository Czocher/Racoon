package org.czocher.raccoon.models;

public interface OrderModel extends DataModel {

	int getOrderId();

	void setOrderId(int id);

	String getProduct();

	void setProduct(String product);

	int getQuantity();

	void setQuantity(int quantity);

	ClientModel getClient();

	void setClient(ClientModel clientModel);

	OrderModel getOrderById(int id);

}
