package org.czocher.raccoon.views.index;

import org.czocher.raccoon.presenters.index.IndexPresenter;
import org.czocher.raccoon.views.View;

public interface IndexView extends View {

	static String TAG = "index";

	IndexPresenter getPresenter();

	void setPresenter(IndexPresenter presenter);

}
