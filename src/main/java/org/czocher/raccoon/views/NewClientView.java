package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.NewClientPresenter;

public interface NewClientView extends View {

	static String TAG = "new/client";

	NewClientPresenter getPresenter();

	void setPresenter(NewClientPresenter presenter);

}
