package org.czocher.raccoon;

import java.io.IOException;
import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import org.czocher.raccoon.presenters.client.impl.ClientCreatePresenterImpl;
import org.czocher.raccoon.presenters.client.impl.ClientDeletePresenterImpl;
import org.czocher.raccoon.presenters.client.impl.ClientEditPresenterImpl;
import org.czocher.raccoon.presenters.client.impl.ClientListPresenterImpl;
import org.czocher.raccoon.presenters.client.impl.ClientPresenterImpl;
import org.czocher.raccoon.presenters.index.impl.IndexPresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderCreatePresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderDeletePresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderEditPresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderListPresenterImpl;
import org.czocher.raccoon.presenters.order.impl.OrderPresenterImpl;
import org.czocher.raccoon.presenters.orderitem.impl.OrderItemCreatePresenterImpl;
import org.czocher.raccoon.presenters.orderitem.impl.OrderItemDeletePresenterImpl;
import org.czocher.raccoon.presenters.orderitem.impl.OrderItemEditPresenterImpl;
import org.czocher.raccoon.presenters.orderitem.impl.OrderItemPresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductCreatePresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductDeletePresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductEditPresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductListPresenterImpl;
import org.czocher.raccoon.presenters.product.impl.ProductPresenterImpl;
import org.czocher.raccoon.views.client.ClientCreateView;
import org.czocher.raccoon.views.client.ClientDeleteView;
import org.czocher.raccoon.views.client.ClientEditView;
import org.czocher.raccoon.views.client.ClientListView;
import org.czocher.raccoon.views.client.ClientView;
import org.czocher.raccoon.views.client.impl.ClientCreateViewImpl;
import org.czocher.raccoon.views.client.impl.ClientDeleteViewImpl;
import org.czocher.raccoon.views.client.impl.ClientEditViewImpl;
import org.czocher.raccoon.views.client.impl.ClientListViewImpl;
import org.czocher.raccoon.views.client.impl.ClientViewImpl;
import org.czocher.raccoon.views.index.IndexView;
import org.czocher.raccoon.views.index.impl.IndexViewImpl;
import org.czocher.raccoon.views.order.OrderCreateView;
import org.czocher.raccoon.views.order.OrderDeleteView;
import org.czocher.raccoon.views.order.OrderEditView;
import org.czocher.raccoon.views.order.OrderListView;
import org.czocher.raccoon.views.order.OrderView;
import org.czocher.raccoon.views.order.impl.OrderCreateViewImpl;
import org.czocher.raccoon.views.order.impl.OrderDeleteViewImpl;
import org.czocher.raccoon.views.order.impl.OrderEditViewImpl;
import org.czocher.raccoon.views.order.impl.OrderListViewImpl;
import org.czocher.raccoon.views.order.impl.OrderViewImpl;
import org.czocher.raccoon.views.orderitem.OrderItemCreateView;
import org.czocher.raccoon.views.orderitem.OrderItemDeleteView;
import org.czocher.raccoon.views.orderitem.OrderItemEditView;
import org.czocher.raccoon.views.orderitem.OrderItemView;
import org.czocher.raccoon.views.orderitem.impl.OrderItemCreateViewImpl;
import org.czocher.raccoon.views.orderitem.impl.OrderItemDeleteViewImpl;
import org.czocher.raccoon.views.orderitem.impl.OrderItemEditViewImpl;
import org.czocher.raccoon.views.orderitem.impl.OrderItemViewImpl;
import org.czocher.raccoon.views.product.ProductCreateView;
import org.czocher.raccoon.views.product.ProductDeleteView;
import org.czocher.raccoon.views.product.ProductEditView;
import org.czocher.raccoon.views.product.ProductListView;
import org.czocher.raccoon.views.product.ProductView;
import org.czocher.raccoon.views.product.impl.ProductCreateViewImpl;
import org.czocher.raccoon.views.product.impl.ProductDeleteViewImpl;
import org.czocher.raccoon.views.product.impl.ProductEditViewImpl;
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
	private ProductListView productListView;
	private ProductView productView;
	private OrderView orderView;
	private OrderItemView orderItemView;
	private ClientCreateView clientCreateView;
	private ProductCreateView productCreateView;
	private OrderCreateView orderCreateView;
	private OrderItemCreateView orderItemCreateView;
	private ClientDeleteView clientDeleteView;
	private OrderDeleteView orderDeleteView;
	private ProductDeleteView productDeleteView;
	private OrderItemDeleteView orderItemDeleteView;
	private ClientEditView clientEditView;
	private OrderEditView orderEditView;
	private OrderItemEditView orderItemEditView;
	private ProductEditView productEditView;

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

		if (uri.matches("^/" + IndexView.TAG)) {
			routeIndex(request);
		} else if (uri.matches("^/" + ClientListView.TAG)) {
			routeClientList(request);
		} else if (uri.matches("^/" + ProductListView.TAG)) {
			routeProductList(request);
		} else if (uri.matches("^/" + OrderListView.TAG)) {
			routeOrderList(request);
		} else if (uri.matches("^/" + ClientView.TAG)) {
			routeClient(request);
		} else if (uri.matches("^/" + ProductView.TAG)) {
			routeProduct(request);
		} else if (uri.matches("^/" + OrderView.TAG)) {
			routeOrder(request);
		} else if (uri.matches("^/" + OrderItemView.TAG)) {
			routeOrderItem(request);
		} else if (uri.matches("^/" + ClientCreateView.TAG)) {
			routeClientCreate(request);
		} else if (uri.matches("^/" + ProductCreateView.TAG)) {
			routeProductCreate(request);
		} else if (uri.matches("^/" + OrderCreateView.TAG)) {
			routeOrderCreate(request);
		} else if (uri.matches("^/" + OrderItemCreateView.TAG)) {
			routeOrderItemCreate(request);
		} else if (uri.matches("^/" + ClientDeleteView.TAG)) {
			routeClientDelete(request);
		} else if (uri.matches("^/" + ProductDeleteView.TAG)) {
			routeProductDelete(request);
		} else if (uri.matches("^/" + OrderDeleteView.TAG)) {
			routeOrderDelete(request);
		} else if (uri.matches("^/" + OrderItemDeleteView.TAG)) {
			routeOrderItemDelete(request);
		} else if (uri.matches("^/" + ClientEditView.TAG)) {
			routeClientEdit(request);
		} else if (uri.matches("^/" + OrderEditView.TAG)) {
			routeOrderEdit(request);
		} else if (uri.matches("^/" + OrderItemEditView.TAG)) {
			routeOrderItemEdit(request);
		} else if (uri.matches("^/" + ProductEditView.TAG)) {
			routeProductEdit(request);
		} else {
			throw new HTTPException(404, "File not found.");
		}

		System.out.println("Request for " + uri + " handled: " + request.getResponseCode());
	}

	private void routeProductEdit(final HttpExchange request) throws HTTPException {
		if (productEditView == null) {
			productEditView = new ProductEditViewImpl();
		}

		new ProductEditPresenterImpl(productEditView).go(request);
	}

	private void routeOrderItemEdit(final HttpExchange request) throws HTTPException {
		if (orderItemEditView == null) {
			orderItemEditView = new OrderItemEditViewImpl();
		}

		new OrderItemEditPresenterImpl(orderItemEditView).go(request);
	}

	private void routeOrderEdit(final HttpExchange request) throws HTTPException {
		if (orderEditView == null) {
			orderEditView = new OrderEditViewImpl();
		}

		new OrderEditPresenterImpl(orderEditView).go(request);
	}

	private void routeClientEdit(final HttpExchange request) throws HTTPException {
		if (clientEditView == null) {
			clientEditView = new ClientEditViewImpl();
		}

		new ClientEditPresenterImpl(clientEditView).go(request);
	}

	private void routeOrderItemDelete(final HttpExchange request) throws HTTPException {
		if (orderItemDeleteView == null) {
			orderItemDeleteView = new OrderItemDeleteViewImpl();
		}

		new OrderItemDeletePresenterImpl(orderItemDeleteView).go(request);
	}

	private void routeProductDelete(final HttpExchange request) throws HTTPException {
		if (productDeleteView == null) {
			productDeleteView = new ProductDeleteViewImpl();
		}

		new ProductDeletePresenterImpl(productDeleteView).go(request);
	}

	private void routeOrderDelete(final HttpExchange request) throws HTTPException {
		if (orderDeleteView == null) {
			orderDeleteView = new OrderDeleteViewImpl();
		}

		new OrderDeletePresenterImpl(orderDeleteView).go(request);

	}

	private void routeClientDelete(final HttpExchange request) throws HTTPException, IOException {
		if (clientDeleteView == null) {
			clientDeleteView = new ClientDeleteViewImpl();
		}

		new ClientDeletePresenterImpl(clientDeleteView).go(request);
	}

	private void routeOrderItemCreate(final HttpExchange request) throws HTTPException, IOException {

		if (orderItemCreateView == null) {
			orderItemCreateView = new OrderItemCreateViewImpl();
		}

		new OrderItemCreatePresenterImpl(orderItemCreateView).go(request);

	}

	private void routeOrderCreate(final HttpExchange request) throws HTTPException {

		if (orderCreateView == null) {
			orderCreateView = new OrderCreateViewImpl();
		}

		new OrderCreatePresenterImpl(orderCreateView).go(request);
	}

	private void routeProductCreate(final HttpExchange request) throws HTTPException {

		if (productCreateView == null) {
			productCreateView = new ProductCreateViewImpl();
		}

		new ProductCreatePresenterImpl(productCreateView).go(request);
	}

	private void routeClientCreate(final HttpExchange request) throws HTTPException {

		if (clientCreateView == null) {
			clientCreateView = new ClientCreateViewImpl();
		}

		new ClientCreatePresenterImpl(clientCreateView).go(request);
	}

	private void routeOrderItem(final HttpExchange request) throws HTTPException, IOException {

		if (orderItemView == null) {
			orderItemView = new OrderItemViewImpl();
		}

		new OrderItemPresenterImpl(orderItemView).go(request);

	}

	private void routeOrder(final HttpExchange request) throws HTTPException, IOException {

		if (orderView == null) {
			orderView = new OrderViewImpl();
		}

		new OrderPresenterImpl(orderView).go(request);

	}

	private void routeProduct(final HttpExchange request) throws HTTPException, IOException {

		if (productView == null) {
			productView = new ProductViewImpl();
		}

		new ProductPresenterImpl(productView).go(request);

	}

	private void routeClient(final HttpExchange request) throws HTTPException, IOException {

		if (clientView == null) {
			clientView = new ClientViewImpl();
		}

		new ClientPresenterImpl(clientView).go(request);

	}

	private void routeOrderList(final HttpExchange request) throws HTTPException, IOException {

		if (orderListView == null) {
			orderListView = new OrderListViewImpl();
		}

		new OrderListPresenterImpl(orderListView).go(request);

	}

	private void routeProductList(final HttpExchange request) throws HTTPException, IOException {

		if (productListView == null) {
			productListView = new ProductListViewImpl();
		}

		new ProductListPresenterImpl(productListView).go(request);

	}

	private void routeClientList(final HttpExchange request) throws HTTPException, IOException {

		if (clientListView == null) {
			clientListView = new ClientListViewImpl();
		}

		new ClientListPresenterImpl(clientListView).go(request);

	}

	private void routeIndex(final HttpExchange request) throws HTTPException, IOException {

		if (indexView == null) {
			indexView = new IndexViewImpl();
		}

		new IndexPresenterImpl(indexView).go(request);

	}

	private static void openDatabaseConnection() {
		if (!Base.hasConnection()) {
			Base.open("com.mysql.jdbc.Driver", "jdbc:mysql://localhost/raccoon?characterEncoding=utf8", "raccoon", "raccoonpasswd");
		}
	}
}