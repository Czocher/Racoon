package org.czocher.raccoon.presenters.client;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.client.ClientEditView;

public interface ClientEditPresenter extends Presenter {

	Client getClient();

	void setClient(Client client);

	ClientEditView getView();

	void setView(ClientEditView view);

}
