package org.czocher.raccoon.presenters.index;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.index.IndexView;

public interface IndexPresenter extends Presenter {

	IndexView getView();

	void setView(IndexView view);

}
