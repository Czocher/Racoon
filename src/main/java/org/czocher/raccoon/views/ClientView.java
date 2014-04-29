package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.ClientPresenter;

public interface ClientView extends View {

	static String TAG = "show/client";

	ClientPresenter getPresenter();

	void setPresenter(ClientPresenter presenter);

}
