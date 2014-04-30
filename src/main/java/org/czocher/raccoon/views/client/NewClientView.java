package org.czocher.raccoon.views.client;

import org.czocher.raccoon.presenters.client.NewClientPresenter;
import org.czocher.raccoon.views.View;

public interface NewClientView extends View {

	static String TAG = "create/client";

	NewClientPresenter getPresenter();

	void setPresenter(NewClientPresenter presenter);

}
