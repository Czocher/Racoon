package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;

import org.czocher.raccoon.presenters.impl.IndexPresenterImpl;
import org.czocher.raccoon.views.IndexView;
import org.czocher.raccoon.views.impl.IndexViewImpl;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class RequestHandler implements HttpHandler {

	private IndexView indexView;

	@Override
	public void handle(final HttpExchange t) throws IOException {
		final String uri = t.getRequestURI().getRawPath();
		int code = 200;
		String response;

		if (uri.matches("^/" + IndexView.TAG)) {
			if (indexView == null) {
				indexView = new IndexViewImpl();
			}
			response = new IndexPresenterImpl(indexView).go();
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
}