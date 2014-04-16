package org.czocher.raccoon.presenters;

import java.util.List;

import org.czocher.raccoon.models.Client;

public interface ClientListPresenter extends Presenter {

	public List<Client> getClientList();

	public void setClientList(final List<Client> clientList);

}
