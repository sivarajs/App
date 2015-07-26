package meru.app.security;

import meru.app.AppRequest;

public abstract class AppSecurity<T extends AppRequest>{

    public abstract void enforce(T appRequest);

}
