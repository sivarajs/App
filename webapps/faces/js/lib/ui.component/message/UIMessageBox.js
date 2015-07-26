ui.messageBox = {

	getHtml : function() {

		return "<div id='messageBox' class='popup' style='width:500px'>"
				+ "<div class='head popupHead'><div class='title'>Message</div><div class='popupClose'></div></div>"
				+ "<div class='content'/>"
				+ "<div class='foot'><button onclick='ui.messageBox.hide()'>Close</button></div>"
				+ "</div>";

	},

	hide : function() {
		$('.overlay').css("display", "none");
		$('#messageBox').fadeOut(500);
	},

	error : function(ex) {
		if (ex.message) {
			ex = ex.message;
		}
		this.show(ex, "error");
	},

	show : function(message, type) {

		if (!document.nodeExists("messageBox")) {
			document.appendHTML(this.getHtml());
		}

		var msgBox = $('#messageBox');
		$(".content", '#messageBox').html(message);
		$('.overlay').css("display", "block");
		msgBox.hide().fadeIn(500);

	}

};
