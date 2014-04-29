package org.czocher.raccoon.models;

import java.sql.Timestamp;
import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("orders")
@IdName("orderid")
@BelongsTo(parent = Client.class, foreignKeyName = "clientid")
public class Order extends Model {

	public Order() {
		super();
	}

	public Order(final OrderItem orderItem) {
		super();
		addOrderItem(orderItem);
	}

	public Order(final OrderItem orderItem, final boolean save) {
		super();
		addOrderItem(orderItem);

		if (save) {
			saveIt();
		}
	}

	public void addOrderItem(final OrderItem orderItem) {
		add(orderItem);
	}

	public List<OrderItem> getOrderItems() {
		return getAll(OrderItem.class);
	}

	public Timestamp getOrderTimestamp() {
		return getTimestamp("timestamp");
	}

	public void setOrderTimestamp(final Timestamp timestamp) {
		setTimestamp("timestamp", timestamp);
	}

	public Client getClient() {
		return parent(Client.class);
	}

	public void setClient(final Client client) {
		setParent(client);
	}
}
