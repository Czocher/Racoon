package org.czocher.raccoon.models.impl;

import org.czocher.raccoon.models.ClientModel;
import org.czocher.raccoon.models.OrderModel;
import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("orders")
@IdName("orderid")
@BelongsTo(parent = ClientModelImpl.class, foreignKeyName = "clientid")
public class OrderModelImpl extends Model implements OrderModel {

	@Override
	public String getProduct() {
		return getString("product");
	}

	@Override
	public void setProduct(final String product) {
		set("product", product);
	}

	@Override
	public int getQuantity() {
		return getInteger("quantity");
	}

	@Override
	public void setQuantity(final int quantity) {
		setInteger("quantity", quantity);
	}

	@Override
	public ClientModelImpl getClient() {
		return parent(ClientModelImpl.class);
	}

	@Override
	public void setClient(final ClientModel client) {
		setParent((ClientModelImpl) client);
	}

	@Override
	public int getOrderId() {
		return (int) getId();
	}

	@Override
	public void setOrderId(final int id) {
		setId(id);
	}

	@Override
	public void remove() {
		delete(true);
	}

	@Override
	public OrderModel getOrderById(final int id) {
		return findById(id);
	}

}
