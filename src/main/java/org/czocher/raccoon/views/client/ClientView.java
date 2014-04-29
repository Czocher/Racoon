package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.ClientPresenter;
import org.czocher.raccoon.views.View;

public interface ClientView extends View {

	static String TAG = "show/client";

	ClientPresenter getPresenter();

	void setPresenter(ClientPresenter presenter);

}
