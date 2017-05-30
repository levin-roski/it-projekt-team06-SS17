package de.worketplace.team06.shared;

import com.google.gwt.user.client.rpc.RemoteService;
import com.google.gwt.user.client.rpc.RemoteServiceRelativePath;

import de.worketplace.team06.shared.bo.LoginInfo;


@RemoteServiceRelativePath("login")
public interface LoginService extends RemoteService {
  public LoginInfo login(String requestUri);
}