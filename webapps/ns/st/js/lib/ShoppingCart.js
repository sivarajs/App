function loadShoppingCart() {
	shoppingCart.load();
}

var shoppingCart = {

	clear : function() {
		this.itemCount(0);
		var scTable = document.getElementById("shoppingCartTable");
		if (sys.isValid(scTable))
		   scTable.uiComp.emptyTable();
	},
	load : function() {
		var sc = entityStore.get("SessionShoppingCart",1);
		this.itemCount(sc);
	},	
		
	addItem : function(itemId, qty) {

		if (!sys.isValid(itemId) || !sys.isValid(qty)) {
			sys.alert("Unable to process the request #" + itemId + "#" + qty);
			return;
		}

		var o = {};
		o["productLineItem"] = {};
		o["productLineItem"]["id"] = ""+itemId;
		o["quantity"] = "" + qty;

		var sc = entityStore.save("ShoppingCartLineItem", o);
		this.itemCount(sc);
		
	},
	
	itemCount : function(sc) {
		var v = sys.isValid(sc["itemCount"]) ? sc["itemCount"] : "0"; 
		$(".scCount").text(v);
	},
	
	checkout : function() {
		var c = $(".scCount").text()
		c = parseInt(c);
		
		if (c == 0) {
		  alert("The shopping cart is empty");
		  return;
		}
		checkout.start();
	}
}
