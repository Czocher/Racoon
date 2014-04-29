package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.ClientListPresenter;
import org.czocher.raccoon.views.View;

public interface ClientListView extends View {

	static String TAG = "show/clients";

	ClientListPresenter getPresenter();

	void setPresenter(ClientListPresenter presenter);

}
