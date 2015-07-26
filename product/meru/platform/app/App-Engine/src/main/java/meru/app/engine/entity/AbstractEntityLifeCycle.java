package meru.app.engine.entity;

import java.util.List;

import meru.app.AppContext;
import meru.app.AppRequest;
import meru.app.config.AppConfig;
//import app.domain.AppEntityState;
import meru.app.engine.AppEngine;
import meru.app.service.ServiceManager;
import meru.persistence.EntityQuery;

public abstract class AbstractEntityLifeCycle<T> implements EntityLifeCycle<T> {

  protected AppConfig appConfig;
  protected AppContext appContext;
  protected EntityAppEngine appEngine;
  protected ServiceManager serviceManager;

  void setApplicationContext(AppContext applicationContext,
                             AppConfig appConfig,
                             AppEngine applicationEngine,
                             ServiceManager serviceManager) {
    this.appContext = applicationContext;
    this.appConfig = appConfig;
    this.appEngine = (EntityAppEngine) applicationEngine;
    this.serviceManager = serviceManager;
  }

  public Class<?>[] observeableEntities() {
    return null;
  }

  public String getSessionId() {
    return AppRequest.currentRequest()
                     .getSessionId();
  }

  public Long getLoggedInUserId() {
    return AppRequest.currentRequest()
                     .getLoggedInUserId();
  }

  public void init() {

  }

  @Override
  public boolean preCreate(T entity) {
    return true;
  }

  @Override
  public Object postCreate(T entity) {
    return null;
  }

  @Override
  public boolean preModify(T entity) {
    return true;
  }

  @Override
  public Object postModify(T entity) {
    return null;
  }

  @Override
  public boolean preDelete(Class<T> resourceClass,
                           Object id) {
    return true;
  }

  @Override
  public Object postDelete(Class<T> resourceClass,
                           Object id) {
    return null;
  }

  @Override
  public boolean preDelete(T entity) {
    return true;
  }

  @Override
  public Object postDelete(T entity) {
    return null;
  }

  @Override
  public List<T> preGet(EntityQuery<T> resourceFilter) {
    return null;
  }

  @Override
  public List<T> postGet(EntityQuery<T> resourceFilter,
                         List<T> entityList) {
    return null;
  }

  @Override
  public T preGetFirst(EntityQuery<T> resourceFilter) {
    return null;
  }

  @Override
  public T postGetFirst(EntityQuery<T> resourceFilter,
                        T entity) {
    return null;
  }

  @Override
  public T preGet(Class<T> entityClass,
                  Object id) {
    return null;
  }

  @Override
  public T postGet(T entity) {
    return null;
  }

  public void onObserveableEntityCreate(Object entity) {

  }

  public void onObserveableEntityModify(Object entity) {

  }

  public void onObserveableEntityDelete(Object entity) {

  }
}
