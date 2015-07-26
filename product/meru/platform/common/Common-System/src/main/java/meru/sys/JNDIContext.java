package meru.sys;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

public class JNDIContext extends InitialContext {

    private static final String JNDI_ROOT = "java:comp/env";

    public JNDIContext() throws NamingException {
        super();
    }

    private static Context getContext() throws NamingException {
        Context initCtx = new InitialContext();
        Context envCtx = (Context) initCtx.lookup(JNDI_ROOT);

        return envCtx;
    }

    @Override
    public Object lookup(String name) throws NamingException {

        return getContext().lookup(name);
    }

    @Override
    public void bind(String name,
                     Object object) throws NamingException {
        getContext().bind(name,
                          object);
    }

}
