package org.czocher.raccoon.presenters;

import org.czocher.raccoon.views.IndexView;

public interface IndexPresenter extends Presenter {

	IndexView getView();

	void setView(IndexView view);
}
