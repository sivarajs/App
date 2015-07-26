
function initBuyButtons() {

	$(".buyDiv").each(function(idx) {
		this.cartPipe = new CartPipe(this);
	});

}

var checkout = {

	onSelect : function(li) {

	},
	start : function() {
		var cw = document.getElementById("checkoutDlg");
		if (!sys.isValid(cw)) {
			cw = ui.page.section.appendXHtml("store/cart/inc/Checkout.xhtml?a=ns&amp;b=0");
		}

		cw.uiComp.show();
	},
	proceed : function() {
		var sof = document.getElementById("salesOrderForm");
		var e = sof.uiComp.submit();

		if (!sys.isValid(e)) {
			alert("Unable to process your order");
		}
		else {
		  document.getElementById("checkoutDlg").uiComp.hide();
		  shoppingCart.clear();
		  alert("Your order has been successfully sumbmitted");
		}
	},
	prev : function() {

	}

}

function userLoggedIn() {
	checkoutLoginListener.proceed(true);
}

var checkoutLoginListener = {
	onSelect : function(li) {

	},
	proceed : function(proceed) {
		if (proceed) {
			document.getElementById("checkoutWz").uiComp.selectBC("n", null,
					false);
			return;
		}
		document.getElementById("loginFrom").submit();
		return false;
	},
	prev : function() {

	}
}

var checkoutProfileListener = {
		contentAdded : false,
		onSelect : function(li) {
			var src = li.getAttribute("src");

			if (!this.contentAdded && sys.isValid(src)) {
				ui.page.section.attachXHtml(src, li.getAttribute("cid"));
				this.contentAdded = true;
			}
		},
		proceed : function(proceed) {
			var entity = document.getElementById("customerProfile").uiComp.submit();
			document.getElementById("salesOrderCustomerId").value = entity["id"];
			if (sys.isValid(entity))
				return true;
			return false;
		},
		prev : function() {

		}
	}

var checkoutAddressListener = {
    contentAdded : false,
	setAddress : function(id, address) {
		document.getElementById("delAddressId").value = id;
		var a = $(document.getElementById("delAddress"));
		if (typeof address == "object") {
			var adr = {};
			adr["customerAddress"] = address;
			var adrStr = "#{customerAddress.address.address}, #{customerAddress.address.area.name}, #{customerAddress.address.city.name}, #{customerAddress.address.state.name}, #{customerAddress.address.country.name} - #{customerAddress.address.pinCode}"
			address = el.substitute(adrStr, adr);	
		}
		a.text(address);
	},

	onSelect : function(li) {
		var src = li.getAttribute("src");
		if (!this.contentAdded && sys.isValid(src)) {
			ui.page.section.attachXHtml(src, li.getAttribute("cid"));
			this.contentAdded = true;
		}
	},

	proceed : function() {

		var ads = $('input[name=deliveryAddress]');
		var sa;

		for (var i = 0, a; a = ads[i++];) {
			if (a.checked)
				sa = a;
		}

		if (sys.isValid(sa)) {
			var a = $("p", sa.parentNode).text();
			this.setAddress(sa.value, a);
			return true;
		}
		var f = document.getElementById("coAddressForm").uiComp;
		
		var entity = f.submit();
		if (sys.isValid(entity))
			alert(JSON.stringify(entity,null,2));
			this.setAddress(entity["id"], entity);
			return true;

		return false;
	},
	prev : function() {

	},
	onAddressSelect : function() {

	},
	onNewAddress : function() {
		$('#coNewAddressDiv').css('display', 'block');
	}

}
