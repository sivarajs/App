var APP_HOME = "/";

(function() {
	if (typeof initApp == "function")
		initApp();
})();

var FACES_HOME = "/faces/";
var THEME_NAME = "basic";
var THEME_HOME = FACES_HOME + "theme/" + THEME_NAME + "/";
var webServer;
var entityStore;

(function() {
	window.callOnLoad(function() {
		// jQuery.support.cors = true;
		webServer = new WebServer(APP_HOME);
		entityStore = new EntityStore(new WebServer(APP_HOME + "e/"));
		xhtmlParser.parse($("body")[0]);

		if (!sys.isValid(window.getCookie("TZO"))) {
			var n = new Date().getTimezoneOffset();
			var o = {
				path : "/"
			};
			window.setCookie("TZO", n, o);
		}

	});

})();

var account = {

	onLoginSuccess : function(loc) {
		
		if (loc == 'f' && typeof userLoggedIn == "function") {
			userLoggedIn();
			return;
		}
		
		ui.popup.closeAll();
		if (loc == "customer") window.top.location = window.top.location.reload(true);
		else 
			window.top.location = APP_HOME + loc;
	},

	onLoginFailure : function(msg) {
		$("#loginMsg").text(msg);
	},

	onRegisterSuccess : function(loc) {
		ui.popup.closeAll();
		ui.messageBox
				.show('Thanks for registering with us. <br>We have sent you an email to confirm your registration.');
	},

	onRegisterFailure : function(msg) {
		$("#registerMsg").text(msg);
	}
}
