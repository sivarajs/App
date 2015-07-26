package meru.erp.sales;

public enum SalesOrderState {

  New(1, "New"),
  PendingPayment(2, "Pending Payment"),
  CustomerSubmitted(3, "Customer Submitted"),
  InProcess(4, "In-Process"),
  WaitingForShipment(5, "Waiting For Shipment"),
  Shipped(6, "Shipped"),
  Delivered(7, "Delivered"),
  Completed(8, "Completed"),
  CustomerCancelled(10, "Customer Cancelled"),
  Cancelled(10, "Cancelled");

  private int code;
  private String state;

  private SalesOrderState(int code, String state) {
    this.code = code;
    this.state = state;
  }

  public int getCode() {
    return code;
  }

  public String toString() {
    return state;
  }

  public static SalesOrderState getOrderState(String state) {

    if (state == null) {
      return New;
    }

    for (SalesOrderState orderState : values()) {
      if (orderState.state.equals(state)) {
        return orderState;
      }
    }

    throw new IllegalArgumentException("Unknown OrderState: " + state);
  }
}
