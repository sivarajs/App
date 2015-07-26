package meru.persistence.jpa;

import java.math.BigInteger;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityNotFoundException;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.RollbackException;
import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.Status;
import javax.transaction.SystemException;

import meru.persistence.EntityQuery;
import meru.persistence.PersistentStore;
import meru.transaction.Transaction;
import meru.transaction.TransactionManager;

public class JPADatabaseStore implements PersistentStore, Transaction {

    private EntityManagerFactory mEntityManagerFactory;
    private TransactionManager mTransactionManager = TransactionManager.getInstance();

    private ThreadLocal<EntityManager> mEntityManagerTL = new ThreadLocal<EntityManager>();

    public JPADatabaseStore(String persistentUnit) {

        mEntityManagerFactory = Persistence.createEntityManagerFactory(persistentUnit);
    }

    private EntityManager getEntityManager() {

        EntityManager entityManager = mEntityManagerTL.get();

        if (entityManager == null) {

            entityManager = mEntityManagerFactory.createEntityManager();
            this.mEntityManagerTL.set(entityManager);
        }

        mTransactionManager.enlistTransaction(this);
        
        try {
            if (!entityManager.getTransaction()
                              .isActive()
                    && mTransactionManager.getStatus() == Status.STATUS_ACTIVE) {

                entityManager.getTransaction()
                             .begin();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        return entityManager;
    }

    @Override
    public <T> T createEntity(T entity) {
        getEntityManager().persist(entity);
        return entity;
    }

    @Override
    public <T> T updateEntity(T entity) {
        EntityManager entityManager = getEntityManager();
        entity = entityManager.merge(entity);
        entityManager.persist(entity);
        return entity;
    }

    @Override
    public <T> void deleteEntity(Class<T> entityClass,
                                 Object id) {
        T entity = getEntity(entityClass,
                             id);
        getEntityManager().remove(entity);

    }

    @Override
    public <T> void deleteEntity(T entity) {
        getEntityManager().remove(entity);

    }

    @Override
    public <T> T getEntity(Class<T> entityClass,
                           Object id) {
        T entity = getEntityManager().find(entityClass,
                                           id);
        if (entity == null) {
            throw new EntityNotFoundException("The entity '"
                    + entityClass.getSimpleName() + "' with id '" + id
                    + "' is not found");
        }

        return entity;
    }

    private final <T> JPAEntityQuery<T> convertQuery(EntityQuery<T> query) {
        return (JPAEntityQuery<T>) query;
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> List<T> getEntities(EntityQuery<T> query) {
        return convertQuery(query).buildQuery(getEntityManager())
                                  .getResultList();
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T> T getFirstEntity(EntityQuery<T> query) {
        List<T> result = convertQuery(query).buildQuery(getEntityManager())
                                            .getResultList();
        return result.isEmpty() ? null : result.get(0);
    }

    /*
     * @Override public <T> int countEntities(Class<T> entityClass,
     * JPAEntityQuery query) { // TODO Auto-generated method stub return 0; }
     * 
     * @Override public <T> boolean entityExists(Class<T> entityClass,
     * JPAEntityQuery query) { // TODO Auto-generated method stub return false;
     * }
     */

    @Override
    public long nextSequenceNumber(String sequenceName) {
        EntityManager entityManager = getEntityManager();
        Query query = entityManager.createNativeQuery("select value from core_app_entity_sequence where name='"
                + sequenceName + "' for update");

        BigInteger value = (BigInteger) query.getSingleResult();
        long lValue = value.longValue();
        Query updateQuery = entityManager.createNativeQuery("update core_app_entity_sequence set value = "
                + (lValue + 1) + " where name ='" + sequenceName + "'");
        if (updateQuery.executeUpdate() == 0) {
            // Handle Error
        }
        return lValue;
    }

    @Override
    public <T> EntityQuery<T> createQuery(Class<T> entityClass) {

        JPAEntityQuery<T> jpaQuery = new JPAEntityQuery<T>(entityClass);

        return jpaQuery;
    }

    @Override
    public void closeCurrentThreadSession() {
        EntityManager entityManager = mEntityManagerTL.get();

        if (entityManager != null) {

            mEntityManagerTL.remove();
            entityManager.clear();
            entityManager.close();
        }

    }

    @Override
    public void begin() throws NotSupportedException,
                       SystemException {

        // getEntityManager().getTransaction()
        // .begin();
        getEntityManager();
    }

    @Override
    public void commit() throws RollbackException,
                        HeuristicMixedException,
                        HeuristicRollbackException,
                        SecurityException,
                        IllegalStateException,
                        SystemException {

        EntityManager entityManager = this.mEntityManagerTL.get();
        if (entityManager == null) {
            return;
        }
        try {

            entityManager.getTransaction()
                         .commit();
        } catch (RuntimeException ex) {
            try {
                entityManager.getTransaction()
                             .rollback();
            } catch (Exception e) {
            }
            throw ex;
        } catch (Exception ex) {
            try {
                entityManager.getTransaction()
                             .rollback();
            } catch (Exception e) {
            }

            throw new RuntimeException(ex);
        } finally {
            closeSession();

        }
    }

    @Override
    public int getStatus() throws SystemException {
        return 0;
    }

    @Override
    public void rollback() throws IllegalStateException,
                          SecurityException,
                          SystemException {
        EntityManager entityManager = this.mEntityManagerTL.get();
        if (entityManager == null) {
            return;
        }
        try {
            entityManager.getTransaction()
                         .rollback();
        } catch (IllegalStateException e) {
            //
        } finally {
            closeSession();
        }
    }

    @Override
    public void setRollbackOnly() throws IllegalStateException,
                                 SystemException {

    }

    @Override
    public void setTransactionTimeout(int arg0) throws SystemException {
    }

    @Override
    public void closeSession() {
        closeCurrentThreadSession();
    }
}
