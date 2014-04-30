package org.czocher.raccoon.presenters.client;

import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.client.ClientCreateView;

public interface ClientCreatePresenter extends Presenter {

	ClientCreateView getView();

	void setView(ClientCreateView view);

}
