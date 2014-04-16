package org.czocher.raccoon;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.net.URL;
import java.util.Locale;

import org.czocher.raccoon.presenters.impl.IndexPresenterImpl;
import org.czocher.raccoon.views.IndexView;
import org.czocher.raccoon.views.impl.IndexViewImpl;
import org.javalite.activejdbc.Base;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import freemarker.template.Configuration;
import freemarker.template.TemplateExceptionHandler;

public class AppDriver {

	/*
	 * Eclipse Kepler Java 8 patches:
	 * http://download.eclipse.org/eclipse/updates/4.3-P-builds/
	 * http://danieldietrich.net/p2/m2e/snapshot/java8/e43
	 */

	public static final Configuration TEMPL = new Configuration();

	public static void main(final String[] args) throws IOException {

		openDatabaseConnection();
		configureTemplates();
		startHTTPServer();

	}

	private static void configureTemplates() {
		TEMPL.setClassForTemplateLoading(AppDriver.class, "templates");
		TEMPL.setDefaultEncoding("UTF-8");
		TEMPL.setLocale(Locale.US);
		TEMPL.setTemplateExceptionHandler(TemplateExceptionHandler.RETHROW_HANDLER);
	}

	private static void startHTTPServer() throws IOException {
		final HttpServer server = HttpServer.create(new InetSocketAddress("127.0.0.1", 8000), 0);
		server.createContext("/", new RequestHandler());
		server.createContext("/static", new StaticRequestHandler());
		server.setExecutor(null); // creates a default executor
		server.start();

		System.out.println("Server listening on: http:/" + server.getAddress());
	}

	private static void openDatabaseConnection() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
	}

	static class RequestHandler implements HttpHandler {

		private IndexView indexView;

		@Override
		public void handle(final HttpExchange t) throws IOException {
			final String uri = t.getRequestURI().getRawPath();
			int code = 200;

			String response = "No URL";
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
			System.out.println("Request for " + uri + " handled: " + code + " " + response);
		}
	}

	static class StaticRequestHandler implements HttpHandler {

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
}
