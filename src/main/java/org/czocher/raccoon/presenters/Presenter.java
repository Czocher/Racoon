package org.czocher.raccoon.presenters;

import org.czocher.raccoon.HTTPException;

import com.sun.net.httpserver.HttpExchange;

public interface Presenter {

	void go(HttpExchange request) throws HTTPException;

}
