package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.models.OrderItem;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.client.impl.ClientListPresenterImpl;
import org.czocher.raccoon.presenters.client.impl.ClientPresenterImpl;
import org.czocher.raccoon.presenters.client.impl.NewClientPresenterImpl;
import org.czocher.raccoon.presenters.index.impl.IndexPresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderListPresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderPresenterImpl;
import org.czocher.raccoon.presenters.orderitem.impl.OrderItemPresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductListPresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductPresenterImpl;
import org.czocher.raccoon.views.client.ClientListView;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.client.NewClientView;
import org.czocher.raccoon.views.client.impl.ClientListViewImpl;
import org.czocher.raccoon.views.client.impl.ClientViewImpl;
import org.czocher.raccoon.views.client.impl.NewClientViewImpl;
import org.czocher.raccoon.views.index.IndexView;
import org.czocher.raccoon.views.index.impl.IndexViewImpl;
import org.czocher.raccoon.views.order.OrderListView;
import org.czocher.raccoon.views.order.OrderView;
import org.czocher.raccoon.views.order.impl.OrderListViewImpl;
import org.czocher.raccoon.views.order.impl.OrderViewImpl;
import org.czocher.raccoon.views.orderitem.OrderItemView;
import org.czocher.raccoon.views.orderitem.impl.OrderItemViewImpl;
import org.czocher.raccoon.views.product.ProductListView;
import org.czocher.raccoon.views.product.ProductView;
import org.czocher.raccoon.views.product.impl.ProductListViewImpl;
import org.czocher.raccoon.views.product.impl.ProductViewImpl;
import org.javalite.activejdbc.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

import freemarker.template.Template;
import freemarker.template.TemplateException;

class RequestHandler implements HttpHandler {

	private IndexView indexView;
	private ClientListView clientListView;
	private OrderListView orderListView;
	private ClientView clientView;
	private ProductListViewImpl productListView;
	private ProductViewImpl productView;
	private OrderViewImpl orderView;
	private OrderItemViewImpl orderItemView;
	private NewClientViewImpl newClientView;
	private String response;
	private int code;

	@Override
	public void handle(final HttpExchange request) throws IOException {
		try {
			openDatabaseConnection();
			routeRequest(request);
		} catch (final HTTPException e) {
			handleError(request, e);
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			request.close();
		}
	}

	private void handleError(final HttpExchange request, final HTTPException e) throws IOException {
		final Template template = AppDriver.TEMPL.getTemplate("error.template.ftl");
		final Map<String, Object> values = new HashMap<>();
		final Writer out = new StringWriter();

		values.put("code", e.getCode());
		values.put("message", e.getBody());

		try {
			template.process(values, out);
		} catch (final TemplateException e1) {
			e1.printStackTrace();
		}

		request.sendResponseHeaders(e.getCode(), 0);
		request.getResponseBody().write(out.toString().getBytes());
		request.close();
	}

	private void routeRequest(final HttpExchange request) throws IOException, HTTPException {
		final String uri = request.getRequestURI().getRawPath();
		@SuppressWarnings("unchecked")
		final Map<String, Object> params = (Map<String, Object>) request.getAttribute("parameters");
		code = 200;
		response = "";

		if (uri.matches("^/" + IndexView.TAG)) {
			routeIndex();
		} else if (uri.matches("^/" + ClientListView.TAG)) {
			routeClientList();
		} else if (uri.matches("^/" + ProductListView.TAG)) {
			routeProductList();
		} else if (uri.matches("^/" + OrderListView.TAG)) {
			routeOrderList();
		} else if (uri.matches("^/" + ClientView.TAG)) {
			routeClient(params);
		} else if (uri.matches("^/" + ProductView.TAG)) {
			routeProduct(params);
		} else if (uri.matches("^/" + OrderView.TAG)) {
			routeOrder(params);
		} else if (uri.matches("^/" + OrderItemView.TAG)) {
			routeOrderItem(params);
		} else if (uri.matches("^/" + NewClientView.TAG)) {
			routeNewClient(request, params);
		} else {
			throw new HTTPException(404, "File not found.");
		}

		request.sendResponseHeaders(code, response.getBytes().length);
		final OutputStream os = request.getResponseBody();
		os.write(response.getBytes());

		os.close();
		request.close();
		System.out.println("Request for " + uri + " handled: " + code);
	}

	private void routeNewClient(final HttpExchange request, final Map<String, Object> params) throws HTTPException {
		Client n;
		if (newClientView == null) {
			newClientView = new NewClientViewImpl();
		}
		if (request.getRequestMethod().equals("POST")) {
			if (!params.containsKey("name") || params.get("name") == null || params.get("name").toString().isEmpty()) {
				throw new HTTPException(400, "Bad request.");
			}

			n = new Client(params.get("name").toString());
			n.saveIt();

			request.getResponseHeaders().add("Location", "/" + ClientView.TAG + "?id=" + n.getId());
			code = 307;
			response = "Redirecting...";
		} else {
			response = new NewClientPresenterImpl(newClientView).go();
		}
	}

	private void routeOrderItem(final Map<String, Object> params) throws HTTPException {

		if (orderItemView == null) {
			orderItemView = new OrderItemViewImpl();
		}

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		response = new OrderItemPresenterImpl(orderItemView, OrderItem.findById(id)).go();

	}

	private void routeOrder(final Map<String, Object> params) throws HTTPException {

		if (orderView == null) {
			orderView = new OrderViewImpl();
		}

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		response = new OrderPresenterImpl(orderView, Order.findById(id)).go();

	}

	private void routeProduct(final Map<String, Object> params) throws HTTPException {

		if (productView == null) {
			productView = new ProductViewImpl();
		}

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		response = new ProductPresenterImpl(productView, Product.findById(id)).go();

	}

	private void routeClient(final Map<String, Object> params) throws HTTPException {

		if (clientView == null) {
			clientView = new ClientViewImpl();
		}

		int id = 0;
		try {
			id = Integer.parseInt((String) params.get("id"));
		} catch (final NumberFormatException e) {
			throw new HTTPException(404, "File not found.");
		}

		response = new ClientPresenterImpl(clientView, Client.findById(id)).go();

	}

	private void routeOrderList() throws HTTPException {

		if (orderListView == null) {
			orderListView = new OrderListViewImpl();
		}

		response = new OrderListPresenterImpl(orderListView, Order.findAll()).go();

	}

	private void routeProductList() throws HTTPException {

		if (productListView == null) {
			productListView = new ProductListViewImpl();
		}

		response = new ProductListPresenterImpl(productListView, Product.findAll()).go();

	}

	private void routeClientList() throws HTTPException {
		;
		if (clientListView == null) {
			clientListView = new ClientListViewImpl();
		}

		response = new ClientListPresenterImpl(clientListView, Client.findAll()).go();

	}

	private void routeIndex() throws HTTPException {

		if (indexView == null) {
			indexView = new IndexViewImpl();
		}

		response = new IndexPresenterImpl(indexView).go();

	}

	private static void openDatabaseConnection() {
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
		}
	}
}