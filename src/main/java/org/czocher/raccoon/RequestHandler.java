package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.models.Order;
import org.czocher.raccoon.presenters.impl.ClientListPresenterImpl;
import org.czocher.raccoon.presenters.impl.IndexPresenterImpl;
import org.czocher.raccoon.presenters.impl.OrderListPresenterImpl;
import org.czocher.raccoon.views.ClientListView;
import org.czocher.raccoon.views.IndexView;
import org.czocher.raccoon.views.OrderListView;
import org.czocher.raccoon.views.impl.ClientListViewImpl;
import org.czocher.raccoon.views.impl.IndexViewImpl;
import org.czocher.raccoon.views.impl.OrderListViewImpl;
import org.javalite.activejdbc.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class RequestHandler implements HttpHandler {

	private IndexView indexView;
	private ClientListView clientListView;
	private OrderListView orderListView;

	@Override
	public void handle(final HttpExchange request) throws IOException {
		try {
			openDatabaseConnection();
			routeRequest(request);
		} catch (final HTTPException e) {
			final int responseCode = e.getCode();
			final String responseBody = e.getBody();
			request.sendResponseHeaders(responseCode, responseBody.length());
			request.getResponseBody().write(responseBody.getBytes());
			request.close();
		} catch (final Exception e) {
			e.printStackTrace();
		} finally {
			request.close();
		}
	}

	private void routeRequest(final HttpExchange request) throws IOException, HTTPException {
		final String uri = request.getRequestURI().getRawPath();
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
		} else if (uri.matches("^/" + OrderListView.TAG)) {
			if (orderListView == null) {
				orderListView = new OrderListViewImpl();
			}

			response = new OrderListPresenterImpl(orderListView, Order.findAll()).go();
		} else {
			throw new HTTPException(404, "404: File not found.");
		}

		request.sendResponseHeaders(code, response.length());
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