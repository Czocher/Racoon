package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.models.Product;
import org.czocher.raccoon.presenters.impl.ClientListPresenterImpl;
import org.czocher.raccoon.presenters.impl.ClientPresenterImpl;
import org.czocher.raccoon.presenters.impl.IndexPresenterImpl;
import org.czocher.raccoon.presenters.impl.OrderListPresenterImpl;
import org.czocher.raccoon.presenters.impl.ProductListPresenterImpl;
import org.czocher.raccoon.presenters.impl.ProductPresenterImpl;
import org.czocher.raccoon.views.ClientListView;
import org.czocher.raccoon.views.ClientView;
import org.czocher.raccoon.views.IndexView;
import org.czocher.raccoon.views.OrderListView;
import org.czocher.raccoon.views.ProductListView;
import org.czocher.raccoon.views.ProductView;
import org.czocher.raccoon.views.impl.ClientListViewImpl;
import org.czocher.raccoon.views.impl.ClientViewImpl;
import org.czocher.raccoon.views.impl.IndexViewImpl;
import org.czocher.raccoon.views.impl.OrderListViewImpl;
import org.czocher.raccoon.views.impl.ProductListViewImpl;
import org.czocher.raccoon.views.impl.ProductViewImpl;
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
		final int code = 200;
		String response;

		if (uri.matches("^/" + IndexView.TAG)) {
			if (indexView == null) {
				indexView = new IndexViewImpl();
			}
			response = new IndexPresenterImpl(indexView).go();
		} else if (uri.matches("^/" + ClientListView.TAG)) {
			if (clientListView == null) {
				clientListView = new ClientListViewImpl();
			}

			response = new ClientListPresenterImpl(clientListView, Client.findAll()).go();
		} else if (uri.matches("^/" + ProductListView.TAG)) {
			if (productListView == null) {
				productListView = new ProductListViewImpl();
			}

			response = new ProductListPresenterImpl(productListView, Product.findAll()).go();
		} else if (uri.matches("^/" + OrderListView.TAG)) {
			if (orderListView == null) {
				orderListView = new OrderListViewImpl();
			}

			response = new OrderListPresenterImpl(orderListView, Order.findAll()).go();
		} else if (uri.matches("^/" + ClientView.TAG)) {
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
		} else if (uri.matches("^/" + ProductView.TAG)) {
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

	private static void openDatabaseConnection() {
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
		}
	}
}