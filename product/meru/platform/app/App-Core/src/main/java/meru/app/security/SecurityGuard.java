package meru.app.security;

import meru.app.AppRequest;

public interface SecurityGuard {

    public void guard(AppRequest appRequest);
    
    
}
