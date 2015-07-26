package meru.app.binding.http;

import meru.app.security.AppSecurity;
import meru.app.security.SecurityGuardChain;

public class HttpAppSecurity extends AppSecurity<HttpAppRequest> {

	private SecurityGuardChain mSecurityGuardChain;

	public HttpAppSecurity(SecurityGuardChain securityGuardChain) {
		mSecurityGuardChain = securityGuardChain;
	}

	public void enforce(HttpAppRequest appRequest) {
		if (mSecurityGuardChain != null) {
			mSecurityGuardChain.guard(appRequest);
		}
	}

}
