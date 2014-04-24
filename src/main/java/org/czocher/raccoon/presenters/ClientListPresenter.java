package org.czocher.raccoon.presenters;

import java.util.List;

import org.czocher.raccoon.models.Client;

public interface ClientListPresenter extends Presenter {

	List<Client> getClientList();

	void setClientList(final List<Client> clientList);

}
