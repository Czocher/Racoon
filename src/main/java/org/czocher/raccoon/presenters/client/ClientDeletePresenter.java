package org.czocher.raccoon.presenters.client;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.client.ClientDeleteView;

public interface ClientDeletePresenter extends Presenter {

	ClientDeleteView getView();

	void setView(ClientDeleteView view);

}
