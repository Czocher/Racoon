package org.czocher.raccoon.views;

import org.czocher.raccoon.HTTPException;

public interface View {

	String render() throws HTTPException;

}
