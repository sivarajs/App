package meru.persistence;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.HashMap;
import java.util.Map;

import meru.sys.SystemDate;
import meru.sys.lang.ClassHelper;

public abstract class EntityQuery<T> {

  public static enum AttributeValue {

    NULL("null", "NULL");

    private String value;
    private String sqlValue;

    private AttributeValue(String value, String sqlValue) {
      this.value = value;
      this.sqlValue = sqlValue;
    }

    public String getValue() {
      return value;
    }

    public String getSQLValue() {
      return sqlValue;
    }

  }

  protected Class<T> mEntityClass;
  protected String mOrderBy;
  protected boolean mIsDesc;
  protected int mPageNo;
  protected int mPageSize;

  private Map<String, Object> mQueryMap;

  public EntityQuery(Class<T> entityClass) {
    mEntityClass = entityClass;
    mQueryMap = new HashMap<String, Object>(1);
  }

  public Class<T> getEntityClass() {
    return mEntityClass;
  }

  public EntityQuery<T> setQueryParameters(String queryStr) {
    String[] filters = queryStr.split("&");

    for (String filter : filters) {
      String[] param = filter.split("=");
      if (param.length == 1) {
        throw new RuntimeException("Illegal query attribute : [" + queryStr
            + "]");
      }

      try {
        param[1] = URLDecoder.decode(param[1],
                                     "UTF-8");
      } catch (UnsupportedEncodingException e) {
        throw new RuntimeException(e);
      }

      addQueryParameter(param[0],
                        param[1]);

    }

    setNonQueryParameters();

    return this;
  }

  public EntityQuery<T> setQueryParameters(Map<String, String[]> queryParameters) {

    if (queryParameters != null && !queryParameters.isEmpty()) {

      for (Map.Entry<String, String[]> param : queryParameters.entrySet()) {

        String attrName = (String) param.getKey();
        String value = param.getValue()[0];
        addQueryParameter(attrName,
                          value);

      }

      setNonQueryParameters();

    }

    return this;
  }

  private void setNonQueryParameters() {
    if (mOrderBy != null) {
      addOrderBy(mOrderBy,
                 mIsDesc);
    }

    if (mPageNo > 0) {
      setPaging(mPageNo,
                mPageSize);
    }
  }

  private void addQueryParameter(String attrName,
                                 String value) {

    if (attrName.startsWith("_")) {
      return;
    }

    if (attrName.equals("orderBy")) {
      mOrderBy = value;
    }
    else if (attrName.equals("isDesc")) {
      mIsDesc = "true".equals(value);
    }
    else if (attrName.equals("pageNo")) {
      mPageNo = Integer.parseInt(value);
    }
    else if (attrName.equals("pageSize")) {
      mPageSize = Integer.parseInt(value);
    }
    else {
      Object covertedValue = null;
      String operator = null;
      if (value.startsWith("[")) {

        operator = value.substring(1,
                                   value.indexOf("]"));
        value = value.substring(value.indexOf("]") + 1);
      }
      else {
        operator = "=";
      }

      if (value.startsWith("sysdate")) {
        covertedValue = SystemDate.getInstance()
                                  .currentDate();
      }
      else {

        if (value.equals(AttributeValue.NULL.getValue())) {
          covertedValue = AttributeValue.NULL;
        }
        else {

          covertedValue = ClassHelper.toFieldTypeValue(mEntityClass,
                                                       attrName,
                                                       value,
                                                       operator.equalsIgnoreCase("in"));
        }

      }

      addQueryParameter(attrName,
                        AttributeOperator.getOperator(operator),
                        covertedValue);
    }

  }

  public final EntityQuery<T> addQueryParameter(String name,
                                                Object value) {
    addQueryParameter(name,
                      AttributeOperator.EQUALS,
                      value);
    return this;

  }

  public final EntityQuery<T> addQueryParameter(String name,
                                                AttributeOperator operator,
                                                Object value) {
    addQueryParameterInternal(name,
                              operator,
                              value);
    mQueryMap.put(name,
                  value);

    return this;
  }

  protected EntityQuery<T> addQueryParameterInternal(String name,
                                                     AttributeOperator operator,
                                                     Object value) {

    return this;
  }

  public Object getQueryParameterValue(String name) {
    return mQueryMap.get(name);
  }

  public boolean hasQueryParameter(String name) {
    return mQueryMap.get(name) != null;
  }

  public EntityQuery<T> addOrderBy(String name) {

    return this;
  }

  public EntityQuery<T> addOrderBy(String name,
                                   boolean isDesc) {

    return this;
  }

  protected EntityQuery<T> setPaging(int pageNo,
                                     int pageSize) {

    return this;
  }

}
