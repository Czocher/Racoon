package org.czocher.raccoon.presenters.client;

import java.util.List;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.Presenter;
import org.czocher.raccoon.views.client.ClientListView;

public interface ClientListPresenter extends Presenter {

	List<Client> getClientList();

	void setClientList(final List<Client> clientList);

	ClientListView getView();

	void setView(ClientListView view);

}
