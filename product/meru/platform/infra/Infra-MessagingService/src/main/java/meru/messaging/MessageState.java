package meru.messaging;

public enum MessageState {

  NEW("N"),
  DELIVERED("D"),
  FAILED("F");

  private String state;

  private MessageState(String state) {
    this.state = state;
  }

  public String getState() {
    return state;
  }

}