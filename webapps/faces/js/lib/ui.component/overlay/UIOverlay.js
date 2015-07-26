ui.overlay = {
	show : function() {
		if (!document.getElementById("overlay")) {
			var ol = document.createElement("div");
			ol.setAttribute("id", "overlay");
			$("body")[0].appendChild(ol);
		}
		document.getElementById("overlay").style.display = "";
	},

	hide : function() {
		document.getElementById("overlay").style.display = "none";
	}
}