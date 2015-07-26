package meru.exception;

public class AppEntityWarning extends AppException {

  private static final long serialVersionUID = 1L;

  private Object mEntity;

  public AppEntityWarning(String code, Object entity) {
    super(code);
    mEntity = entity;
  }

  public Object getEntity() {
    return mEntity;
  }
  
  @Override
  public boolean isRollbackOnly() {
    return false;
  }
}
