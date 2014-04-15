package org.czocher.raccoon.models;

import java.util.List;

public interface ClientModel extends DataModel {

	int getIdentificator();

	void setIdentificator(int id);

	String getName();

	void setName(String name);

	List<? extends OrderModel> getOrders();

	void addOrder(OrderModel order);

	void removeOrder(OrderModel order);

}
