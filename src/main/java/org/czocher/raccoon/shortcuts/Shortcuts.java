package org.czocher.raccoon.shortcuts;

import java.io.IOException;
import java.io.OutputStream;

import org.czocher.raccoon.HTTPException;
import org.czocher.raccoon.views.View;

import com.sun.net.httpserver.HttpExchange;

public class Shortcuts {

	public static void RenderViewToResponse(final View view, final HttpExchange request) throws HTTPException {
		final OutputStream os;
		try {
			request.sendResponseHeaders(200, view.render().getBytes().length);

			os = request.getResponseBody();
			os.write(view.render().getBytes());
			os.close();
		} catch (final IOException e) {
			e.printStackTrace();
		}
		request.close();
	}

	public static void Redirect(final String location, final HttpExchange request) {

		request.getResponseHeaders().add("Location", location);
		try {
			request.sendResponseHeaders(307, -1);
		} catch (final IOException e) {
			e.printStackTrace();
		}

	}
}
