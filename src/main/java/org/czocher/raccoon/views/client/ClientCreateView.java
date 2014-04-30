package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.ClientCreatePresenter;
import org.czocher.raccoon.views.View;

public interface ClientCreateView extends View {

	static String TAG = "create/client";

	ClientCreatePresenter getPresenter();

	void setPresenter(ClientCreatePresenter presenter);

}
