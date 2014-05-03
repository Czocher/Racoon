package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.ClientEditPresenter;
import org.czocher.raccoon.views.View;

public interface ClientEditView extends View {

	static String TAG = "edit/client";

	ClientEditPresenter getPresenter();

	void setPresenter(ClientEditPresenter presenter);

}
