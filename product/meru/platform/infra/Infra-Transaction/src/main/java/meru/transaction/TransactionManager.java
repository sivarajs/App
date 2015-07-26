package meru.transaction;

import javax.transaction.HeuristicMixedException;
import javax.transaction.HeuristicRollbackException;
import javax.transaction.NotSupportedException;
import javax.transaction.RollbackException;
import javax.transaction.Status;
import javax.transaction.SystemException;

public class TransactionManager implements Transaction {

    private ThreadLocal<TransactionRegistry> appTransactionRegistry = new ThreadLocal<TransactionRegistry>();

    private static TransactionManager INSTANCE = new TransactionManager();

    public static TransactionManager getInstance() {
        return INSTANCE;
    }

    private TransactionRegistry getAppTranasctionRegistry() {

        TransactionRegistry registry = appTransactionRegistry.get();
        if (registry == null) {
            registry = new TransactionRegistry();
            appTransactionRegistry.set(registry);
        }

        return registry;
    }

    public void enlistTransaction(Transaction appTransaction) {
        getAppTranasctionRegistry().addTransaction(appTransaction);
    }

    public boolean isTransactionActive() throws SystemException {
        return getAppTranasctionRegistry().getStatus() == Status.STATUS_ACTIVE;
    }
    
    @Override
    public void begin() throws NotSupportedException,
                       SystemException {
        getAppTranasctionRegistry().begin();
    }

    @Override
    public void commit() throws RollbackException,
                        HeuristicMixedException,
                        HeuristicRollbackException,
                        SecurityException,
                        IllegalStateException,
                        SystemException {
        try {
            getAppTranasctionRegistry().commit();
        } finally {
            closeSession();
        }
    }

    @Override
    public int getStatus() throws SystemException {
        return getAppTranasctionRegistry().getStatus();
    }

    @Override
    public void rollback() throws IllegalStateException,
                          SecurityException,
                          SystemException {
        try {
            getAppTranasctionRegistry().rollback();
        } catch (Exception e) {
            e.printStackTrace();
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
        TransactionRegistry registry = appTransactionRegistry.get();
        if (registry != null) {
            try {
                registry.closeSession();
            } finally {
                appTransactionRegistry.remove();
            }
        }
    }

}
