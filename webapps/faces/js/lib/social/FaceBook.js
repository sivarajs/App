ui.init_fbLogin = function(node) {
	node.setAttribute("title","Login By Facebook");
	node.onclick = function(e) {
		var url = 'https://www.facebook.com/dialog/oauth?client_id=358125640942503&redirect_uri=https%3A%2F%2Fwww.dewbag.com%2Fcommon%2Fsocial%2Ffb%2FfbLogin.html&state=facebooklogin&display=popup&scope=email&response_type=token';
		fbWin = this.uiComp.show(url,"Login By Facebook", 500, 300);
		checkFBWin();
	}
	
	return new UIWindow(node);
}

var fbWin;

function checkFBWin() {
	  if (fbWin.closed) {
		try {  
		  window.setCookie("UID",fbWin.UID);
		} catch (e){
			//IE hack. ignore
		}
		//window.location.reload(true);  		
	  } else setTimeout("checkFBWin()",1);
}
