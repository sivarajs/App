package meru.app.security;

import java.util.ArrayList;
import java.util.List;

import meru.app.AppRequest;

public class SecurityGuardChain {

    private List<SecurityGuard> mSecurityGuards = new ArrayList<SecurityGuard>(2);

    protected SecurityGuardChain() {

    }

    public SecurityGuardChain chainSecurityGuard(SecurityGuard securityGuard) {
        mSecurityGuards.add(securityGuard);
        return this;
    }
    
    public  List<SecurityGuard> getSecurityGuards() {
        return mSecurityGuards;
    }

    public void guard(AppRequest appRequest) {
        for (SecurityGuard securityGuard : mSecurityGuards) {
            securityGuard.guard(appRequest);
        }
    }
}
