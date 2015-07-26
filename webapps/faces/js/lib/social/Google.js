ui.init_googleLogin = function(node) {
	node.setAttribute("title","Login By Google");
	node.onclick = function(e) {
		var url = 'https://accounts.google.com/o/oauth2/auth?scope=https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.email+https%3A%2F%2Fwww.googleapis.com%2Fauth%2Fuserinfo.profile&state=%2Fprofile&redirect_uri=https%3A%2F%2Fwww.dewbag.com%2Fcommon%2Fsocial%2Fgoogle%2FgLogin.html&response_type=token&client_id=1062338590500.apps.googleusercontent.com';
		goWin = this.show(url,"Login By Google", 500, 400);
		checkGoWin();
	}
	return new UIWindow(node);
}

var goWin;

function checkGoWin() {
	  if (goWin.closed) {
		  try {
		   window.setCookie("UID",goWin.getCookie("UID"));
		  } catch (e){
				//IE hack. ignore
	      }
		 //window.location.reload(true);	      
	  } else setTimeout("checkGoWin()",1);
}

