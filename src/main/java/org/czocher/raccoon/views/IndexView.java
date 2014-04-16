package org.czocher.raccoon.views;

import org.czocher.raccoon.presenters.IndexPresenter;

public interface IndexView extends View {

	static String TAG = "index";

	IndexPresenter getPresenter();

	void setPresenter(IndexPresenter presenter);

}
