package org.czocher.raccoon;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;

import org.javalite.activejdbc.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

public class AppDriver {

	/*
	 * Eclipse Kepler Java 8 patches:
	 * http://download.eclipse.org/eclipse/updates/4.3-P-builds/
	 * http://danieldietrich.net/p2/m2e/snapshot/java8/e43
	 */

	public static void main(final String[] args) throws IOException {

		openDatabaseConnection();
		startHTTPServer();

	}

	private static void startHTTPServer() throws IOException {
		final HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8000), 0);
		server.createContext("/myapp", new MyHandler());
		server.setExecutor(null); // creates a default executor
		server.start();

		System.out.println("Server listening on: http:/" + server.getAddress());
	}

	private static void openDatabaseConnection() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon", "raccoon", "raccoonpasswd");
	}

	static class MyHandler implements HttpHandler {

		@Override
		public void handle(final HttpExchange t) throws IOException {

			final String path = t.getRequestURI().getRawPath();
			String response = "No URL";
			if (path.matches("^/myapp/url")) {
				response = "This is the response for url";
			} else if (path.matches("^/myapp/other_url")) {
				response = "This is the response for other url";
			}

			t.sendResponseHeaders(200, response.length());
			final OutputStream os = t.getResponseBody();
			os.write(response.getBytes());
			os.close();

			t.close();
		}

	}
}
