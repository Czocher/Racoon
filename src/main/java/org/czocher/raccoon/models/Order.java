package org.czocher.raccoon.models;

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

	public Order(final String product, final int quantity) {
		super();
		setProduct(product);
		setQuantity(quantity);
	}

	public Order(final String product, final int quantity, final boolean save) {
		super();
		setProduct(product);
		setQuantity(quantity);

		if (save) {
			saveIt();
		}
	}

	public Order(final String product, final int quantity, final Client client) {
		setProduct(product);
		setQuantity(quantity);
		setClient(client);
	}

	public Order(final String product, final int quantity, final Client client, final boolean save) {
		setProduct(product);
		setQuantity(quantity);
		setClient(client);

		if (save) {
			saveIt();
		}
	}

	public String getProduct() {
		return getString("product");
	}

	public void setProduct(final String product) {
		set("product", product);
	}

	public int getQuantity() {
		return getInteger("quantity");
	}

	public void setQuantity(final int quantity) {
		setInteger("quantity", quantity);
	}

	public Client getClient() {
		return parent(Client.class);
	}

	public void setClient(final Client client) {
		setParent(client);
	}

}
