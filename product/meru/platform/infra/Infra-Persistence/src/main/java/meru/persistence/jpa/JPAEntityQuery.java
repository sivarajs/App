package meru.persistence.jpa;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import meru.persistence.AttributeOperator;
import meru.persistence.EntityQuery;

public class JPAEntityQuery<T> extends EntityQuery<T> {

  private TypedQuery<T> mQuery;

  private StringBuilder mQueryBuilder;
  protected List<Object> mParamValues;

  public JPAEntityQuery(Class<T> entityClass) {
    this(entityClass,
         false);
  }

  public JPAEntityQuery(Class<T> entityClass, boolean isCountQuery) {
    super(entityClass);

    mQueryBuilder = new StringBuilder(20);

    if (isCountQuery) {
      mQueryBuilder.append("select count(o) from ");
    }
    else {
      mQueryBuilder.append("select o from ");
    }
    mQueryBuilder.append(entityClass.getSimpleName())
                 .append(" o");
  }

  public Query getQuery() {
    return mQuery;
  }

  @Override
  protected JPAEntityQuery<T> addQueryParameterInternal(String name,
                                                        AttributeOperator operator,
                                                        Object value) {

    if (mParamValues == null) {
      mQueryBuilder.append(" where ");
      mParamValues = new ArrayList<Object>(1);
    }
    else {
      mQueryBuilder.append(" and ");
    }

    if (value == AttributeValue.NULL || value == null) {
      operator = AttributeOperator.IS;
      mQueryBuilder.append(name)
                   .append(AttributeOperator.IS.getOperatorForQuery())
                   .append("NULL");
    }
    else if (operator == AttributeOperator.IN) {

      mQueryBuilder.append(name)
                   .append(operator.getOperatorForQuery())
                   .append(operator.getValue(value));

    }
    else {

      mQueryBuilder.append(name)
                   .append(operator.getOperatorForQuery())
                   .append("?")
                   .append(mParamValues.size() + 1);
      mParamValues.add(operator.getValue(value));
    }

    return this;
  }

  @Override
  public final JPAEntityQuery<T> addOrderBy(String name) {
    mQueryBuilder.append(" order by ")
                 .append(name);
    return this;
  }

  @Override
  public JPAEntityQuery<T> addOrderBy(String name,
                                      boolean isDesc) {

    addOrderBy(name);
    if (isDesc) {
      mQueryBuilder.append(" desc");
    }

    return this;
  }

  protected Query buildQuery(EntityManager entityManager) {
    mQuery = entityManager.createQuery(mQueryBuilder.toString(),
                                       mEntityClass);

    if (mParamValues != null) {
      int index = 1;
      for (Object value : mParamValues) {
        mQuery.setParameter(index++,
                            value);
      }
    }

    if (mPageSize > 0) {
      mQuery.setMaxResults(mPageSize);
    }

    if (mPageNo > 0) {
      mQuery.setFirstResult(mPageNo * mPageSize);
    }

    return mQuery;
  }
}
