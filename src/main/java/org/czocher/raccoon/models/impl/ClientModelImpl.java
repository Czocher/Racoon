package org.czocher.raccoon.models.impl;

import java.util.List;

import org.czocher.raccoon.models.ClientModel;
import org.czocher.raccoon.models.OrderModel;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("clients")
@IdName("clientid")
public class ClientModelImpl extends Model implements ClientModel {

	@Override
	public String getName() {
		return getString("name");
	}

	@Override
	public void setName(final String name) {
		set("name", name);
	}

	@Override
	public void addOrder(final OrderModel order) {
		add((OrderModelImpl) order);
	}

	@Override
	public void removeOrder(final OrderModel order) {
		remove((OrderModelImpl) order);
	}

	@Override
	public List<? extends OrderModel> getOrders() {
		return getAll(OrderModelImpl.class);
	}

	@Override
	public int getClientId() {
		return (int) getId();
	}

	@Override
	public void setClientId(final int id) {
		setId(id);
	}

	@Override
	public void remove() {
		delete(true);
	}

	@Override
	public ClientModel getClientById(final int id) {
		return findById(id);
	}
}
