package org.czocher.raccoon.models;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("products")
@IdName("productid")
public class Product extends Model {

	public Product() {
		super();
	}

	public Product(final String name) {
		super();
		setName(name);
	}

	public Product(final String name, final boolean save) {
		super();
		setName(name);

		if (save) {
			saveIt();
		}
	}

	public String getName() {
		return getString("name");
	}

	public void setName(final String name) {
		set("name", name);
	}

	public List<OrderItem> getOrderItems() {
		return getAll(OrderItem.class);
	}

	public void addOrderItem(final OrderItem orderItem) {
		add(orderItem);
	}

}
