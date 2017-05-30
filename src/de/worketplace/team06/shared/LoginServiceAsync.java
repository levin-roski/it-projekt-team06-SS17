package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.AsyncCallback;

import de.worketplace.team06.shared.bo.LoginInfo;


public interface LoginServiceAsync {

	public void login(String requestUri, AsyncCallback<LoginInfo> callback);

}
