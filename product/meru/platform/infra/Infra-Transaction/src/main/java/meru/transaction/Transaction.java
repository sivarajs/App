package meru.transaction;

import javax.transaction.UserTransaction;


public interface Transaction extends UserTransaction {

    public void closeSession();
}
