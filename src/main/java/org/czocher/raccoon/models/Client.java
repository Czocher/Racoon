package org.czocher.raccoon.models;

import java.util.List;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("clients")
@IdName("clientid")
public class Client extends Model {

	public Client() {
		super();
	}

	public Client(final String name) {
		super();
		setName(name);
	}

	public Client(final String name, final boolean save) {
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

	public void addOrder(final Order order) {
		add(order);
	}

	public List<Order> getOrders() {
		return getAll(Order.class);
	}

}
