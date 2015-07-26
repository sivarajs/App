package meru.sys.uid;

import java.util.UUID;

public class UUIDGenerator implements UIDGenerator {

  @Override
  public String getUId() {
    return UUID.randomUUID().toString();
  }

  
}
