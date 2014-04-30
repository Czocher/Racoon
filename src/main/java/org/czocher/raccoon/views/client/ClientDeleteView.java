package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.ClientDeletePresenter;
import org.czocher.raccoon.views.View;

public interface ClientDeleteView extends View {

	static String TAG = "delete/client";

	ClientDeletePresenter getPresenter();

	void setPresenter(ClientDeletePresenter presenter);

}
