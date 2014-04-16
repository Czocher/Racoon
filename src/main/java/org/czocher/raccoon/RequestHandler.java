package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.impl.ClientListPresenterImpl;
import org.czocher.raccoon.presenters.impl.IndexPresenterImpl;
import org.czocher.raccoon.views.ClientListView;
import org.czocher.raccoon.views.IndexView;
import org.czocher.raccoon.views.impl.ClientListViewImpl;
import org.czocher.raccoon.views.impl.IndexViewImpl;
import org.javalite.activejdbc.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class RequestHandler implements HttpHandler {

	private IndexView indexView;
	private ClientListView clientListView;

	@Override
	public void handle(final HttpExchange t) throws IOException {
		try {
			openDatabaseConnection();
			routeRequest(t);
		} catch (final Exception e) {
			e.printStackTrace();
		}
	}

	private void routeRequest(final HttpExchange t) throws IOException {
		final String uri = t.getRequestURI().getRawPath();
		int code = 200;
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

			try {
				Client.findById(1).toString();
			} catch (final Exception e) {
				e.printStackTrace();
			}
			response = new ClientListPresenterImpl(clientListView, Client.findAll()).go();
		} else {
			response = "404: File not found.";
			code = 404;
		}

		t.sendResponseHeaders(code, response.length());
		final OutputStream os = t.getResponseBody();
		os.write(response.getBytes());

		os.close();
		t.close();
		System.out.println("Request for " + uri + " handled: " + code);
	}

	private static void openDatabaseConnection() {
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
		}
	}
}