package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.ClientListPresenter;

public interface ClientListView extends View {

	static String TAG = "show/clients";

	ClientListPresenter getPresenter();

	void setPresenter(ClientListPresenter presenter);

}
