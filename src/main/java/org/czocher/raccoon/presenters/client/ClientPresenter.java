package org.czocher.raccoon.presenters.client;

import org.czocher.raccoon.models.Client;
import org.czocher.raccoon.presenters.Presenter;

public interface ClientPresenter extends Presenter {

	Client getClient();

	void setClient(Client client);

}
