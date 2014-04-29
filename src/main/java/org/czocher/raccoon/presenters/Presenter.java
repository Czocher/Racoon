package org.czocher.raccoon.presenters;

import org.czocher.raccoon.HTTPException;

public interface Presenter {

	String go() throws HTTPException;

}
