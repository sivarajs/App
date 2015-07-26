ui.init_popupLink = function(node) {

	node.onclick = function(e) {
		try {
			jsEvent.preventDefault(e);
			if (!sys.isValid(this.popup)) {
				var src = this.getAttribute("src");
				if (sys.isValid(src)) {
					var popup = ui.page.section.appendXHtml(src);
					this.popup = popup;
				}
			}
			this.popup.uiComp.show(this);
		} catch (e) {sys.alert(e)}
		return false;
	}
}
