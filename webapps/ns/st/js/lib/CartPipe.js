function CartPipe(buyDiv) {
	this.buyDiv = $(buyDiv);
	pIdDiv = $(".buyProdId", this.buyDiv);
	this.pId = parseInt($(pIdDiv).text());
	this.addBut = $(".addCart", this.buyDiv)[0];
	if (sys.isValid(this.addBut)) {
		this.qtyInp = $("input", this.buyDiv)[0];
		console.log(this.qtyInp);
		this.rmBut = $(".removeCart", this.buyDiv)[0];
		this.rmBut.onclick = this.removeShoppingCartItem;
	} else
		this.addBut = $(".buyButton", this.buyDiv)[0];

	this.addBut.onclick = this.addShoppingCartItem;
}

CartPipe.prototype.addShoppingCartItem = function(e) {
	
	if (sys.isValid(this.parentNode.cartPipe.qtyInp))
		this.parentNode.cartPipe.incQty();
	this.parentNode.cartPipe.flow();
	$(this.parentNode.cartPipe.rmBut).css("display","block");
}

CartPipe.prototype.removeShoppingCartItem = function(e) {

}

CartPipe.prototype.incQty = function() {
	var qty;
	if (this.qtyInp.value == "") {
		qty = 0;
	} else {
		try {
			qty = parseInt(this.qtyInp.value);
		} catch (e) {
			alert("Please provide a valid value");
			return null;
		}
	}
	qty++;
	this.qtyInp.value = qty;

}

CartPipe.prototype.validQty = function() {

	if (!sys.isValid(this.qtyInp)) return 1; 
	
	var qty;
	if (this.qtyInp.value == "") {
		this.qtyInp.value = "1";
	}

	try {
		qty = parseInt(this.qtyInp.value);
	} catch (e) {
		alert("Please provide a valid value");
		return null;
	}

	if (qty < 0 || isNaN(qty)) {
		alert("Pleaes provide a valid value");
		return null;
	}
	return qty;
}

CartPipe.prototype.flow = function() {

	if (!sys.isValid(this.pId))
		return;

	var pQty = this.validQty();
	if (!sys.isValid(pQty))
		return;

	var imgCont = this.buyDiv.parent().parent();

	if (imgCont[0].className != "imgGrid") {
		imgCont = $(".imgContainer");
	}

	var os = imgCont.offset();
	var px = os.left;
	var py = os.top;

	var cartIcon = $(".cartIcon");
	var cx = cartIcon.offset().left;
	var cy = cartIcon.offset().top;

	var gx = cx - px;
	var gy = cy - py;
	// alert(px+" "+py+" "+cx+" "+cy+" "+gx+" "+gy);
	var img = $("img", imgCont);
	var imgw = img.width() / 3;
	var imgh = img.height() / 3;
	var imgc = img.clone();
	imgc[0].pId = this.pId;
	imgc[0].pQty = pQty;

	imgc.prependTo(imgCont).css({
		'position' : 'absolute',
		'z-index' : '10'
	}).animate({
		opacity : 0.4
	}, 100).animate({
		opacity : 0.1,
		marginLeft : gx,
		marginTop : gy,
		width : imgw,
		height : imgh
	}, 1200, function() {
		$(this).remove();
		if (sys.isValid(this.pId) && sys.isValid(this.pQty)) {
			shoppingCart.addItem(this.pId, this.pQty);
		}
	});
}
