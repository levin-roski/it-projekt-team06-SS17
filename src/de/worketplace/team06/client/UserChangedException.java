package de.worketplace.team06.client;


import java.io.Serializable;

public class UserChangedException extends Exception implements Serializable {

  public UserChangedException() {
    super();
  }

  public UserChangedException(String message) {
    super(message);
  }

}