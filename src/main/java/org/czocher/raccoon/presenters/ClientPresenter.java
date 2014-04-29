package org.czocher.raccoon.presenters;

import org.czocher.raccoon.models.Client;

public interface ClientPresenter extends Presenter {

	Client getClient();

	void setClient(Client client);

}
