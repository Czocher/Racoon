package org.czocher.raccoon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URL;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;

class StaticRequestHandler implements HttpHandler {

	@Override
	public void handle(final HttpExchange t) throws IOException {
		final String uri = t.getRequestURI().getRawPath();
		final OutputStream out = t.getResponseBody();
		final URL url = AppDriver.class.getResource(uri.substring(1));
		final String fileNotFound = "404: File not found.";

		if (url == null) {

			t.sendResponseHeaders(404, fileNotFound.length());
			out.write(fileNotFound.getBytes());

		} else {
			final File resourceFile = new File(AppDriver.class.getResource(uri.substring(1)).getPath()).getCanonicalFile();

			if (!resourceFile.exists() || !resourceFile.canRead()) {

				t.sendResponseHeaders(404, fileNotFound.length());
				out.write(fileNotFound.getBytes());

			} else {

				final byte[] buffer = new byte[1000];
				int count = 0;

				t.sendResponseHeaders(200, 0);

				final FileInputStream ris = new FileInputStream(resourceFile);
				while ((count = ris.read(buffer)) > 0) {
					out.write(buffer, 0, count);
				}

				ris.close();
			}
		}

		System.out.println("Request for " + uri + " handled: " + t.getResponseCode());
		out.close();
		t.close();
	}
}