package org.czocher.raccoon;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.util.Locale;

import org.javalite.activejdbc.Base;

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

		System.out.println("Server listening on: http:/" + server.getAddress() + "/");
	}

	private static void openDatabaseConnection() {
		Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
	}
}
