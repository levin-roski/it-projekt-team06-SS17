package de.worketplace.team06.shared.bo;

import java.io.Serializable;

public class LoginInfo implements Serializable {

	/**
	 * 
	 */
	private boolean loggedIn = false;
	private String loginUrl;
	private String logoutUrl;
	private String googleId;
	private String emailAddress;
	private String nickname;
 
	/**
	 * 
	 * @return
	 */
	public boolean isLoggedIn() {
		return loggedIn;
	}
	
	/**
	 * 
	 * @param loggedIn
	 */
	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	/**
	 * 
	 * @return loginUrl
	 */
	public String getLoginUrl() {
		return loginUrl;
	}

	/**
	 * 
	 * @param loginUrl
	 */
	public void setLoginUrl(String loginUrl) {
		this.loginUrl = loginUrl;
	}

	/**
	 * 
	 * @return logoutUrl
	 */
	public String getLogoutUrl() {
		return logoutUrl;
	}

	/**
	 * 
	 * @param logoutUrl
	 */
	public void setLogoutUrl(String logoutUrl) {
		this.logoutUrl = logoutUrl;
	}

	/**
	 * 
	 * @return emailAdress
	 */
	public String getEmailAddress() {
		return emailAddress;
	}

	/**
	 * 
	 * @param emailAddress
	 */
	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	/**
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * 
	 * @return googleId
	 */
	public String getGoogleId() {
		return googleId;
	  }
	
	/**
	 * 
	 * @param googleId
	 */
	public void setGoogleId(String googleId) {
		this.googleId = googleId;
	}
}

