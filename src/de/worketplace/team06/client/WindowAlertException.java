package de.worketplace.team06.client;


import java.io.Serializable;

public class WindowAlertException extends Exception implements Serializable {

  public WindowAlertException() {
    super();
  }

  public WindowAlertException(String message) {
    super(message);
  }

}