package org.czocher.raccoon.presenters.client;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.client.ClientView;

public interface ClientPresenter extends Presenter {

	Client getClient();

	void setClient(Client client);

	ClientView getView();

	void setView(ClientView view);

}
