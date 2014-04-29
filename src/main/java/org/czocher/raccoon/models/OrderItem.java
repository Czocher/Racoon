package org.czocher.raccoon.models;

import org.javalite.activejdbc.Model;
import org.javalite.activejdbc.annotations.BelongsTo;
import org.javalite.activejdbc.annotations.BelongsToParents;
import org.javalite.activejdbc.annotations.IdName;
import org.javalite.activejdbc.annotations.Table;

@Table("orderitems")
@IdName("orderitemid")
@BelongsToParents({ @BelongsTo(parent = Order.class, foreignKeyName = "orderid"), @BelongsTo(parent = Product.class, foreignKeyName = "productid") })
public class OrderItem extends Model {

	public OrderItem() {
		super();
	}

	public OrderItem(final int quantity) {
		super();
		setQuantity(quantity);
	}

	public OrderItem(final int quantity, final boolean save) {
		super();
		setQuantity(quantity);

		if (save) {
			saveIt();
		}
	}

	public int getQuantity() {
		return getInteger("quantity");
	}

	public void setQuantity(final int quantity) {
		setInteger("quantity", quantity);
	}

}
