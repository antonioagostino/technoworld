var cartProducts = [];
var cartProductsQuantity = [];
var userId;
var totalPrice;
var address = "";
var storeId = -1;

function changeStoreId(id, storeAddress) {
	storeId = id;
	address = storeAddress;
}

function valueCheck() {

	if(($("#cap").val() === "" || $("#city").val() === "" || $("#province").val() === "" ||
		$("#recipient").val() === "" || $("#street").val() === "") && $("#radio2").prop("checked") == true) {
		$("#shipmentAlert").show();
	} else if($("#radio1").prop("checked") == true && storeId == -1){
		$("#storeAlert").show();
	} else {

		if($("#radio2").prop("checked") == true) {
			address = $("#recipient").val() + ",\n" + $("#street").val() + ",\n" + $("#cap").val()
				+ ",\n" + $("#city").val() + ",\n" + $("#province").val();
			storeId = -1;
		}

		$("#shipmentAlert").hide();
		$("#storeAlert").hide();
		$("#paypalTitle").show();
		$('#shipmentcontainer').hide();
		$('#shipmentTitle').hide();

		paypal.Buttons({
			createOrder: function(data, actions) {
					$('#products').hide();
					$('#shipmentcontainer').hide();
					$('#goToPayment').removeClass("col-sm-3");
					$('#goToPayment').addClass("col-sm-12");
					$('#goToPayment').css({"right": "0%", "position":"static", "padding" : "0"});
					$('#rowTotalPrice').css({"margin": "2%"});
					return actions.order.create({
						purchase_units: [{
							amount: {
								value: totalPrice
							}
						}]
					});
				},
			onApprove: function(data, actions) {
				return actions.order.capture().then(function(details) {
					$('#goToPayment').hide();
					$('#paymentWaitingBox').show();

					return $.ajax({
						method: "POST",
						url: "completedpayment",
						headers: {
							'content-type': 'application/json'
						},
						data: JSON.stringify({
							orderID: data.orderID,
							userID: userId,
							products: cartProducts,
							productsQuantity: cartProductsQuantity,
							address: address,
							amount: totalPrice,
							store: storeId
						})
					}).done(function(msg) {
						$('#paymentWaitingBox').hide();
						if(msg == 1)
							$('#successfullPurchase').show();
						else
							$('#failedPurchase').show();
					});
				});
			}
		}).render('#paypal-button-container');
	}
}

function addCardProduct(productId, quantity) {
	cartProducts.push(productId);
	cartProductsQuantity.push(quantity);
}

function setUserId(Id) {
	userId = Id;
}

function setTotalPrice(price) {
	totalPrice = price.toFixed(2);
}

function removeCartProduct(idProduct) {
	
	var rowId = "#rowProduct"+idProduct;
	$(rowId).remove();

	var indexToRemove = -1;

	for (var i = 0; i < cartProducts.length; i++){
		if(cartProducts[i] == idProduct){
			indexToRemove = i;
		}
	}

	if(indexToRemove > 0) {
		cartProducts.splice(indexToRemove, 1);
		cartProductsQuantity.splice(indexToRemove, 1);
	}

	$.ajax({
		type: "GET",
		url: "removeCartProduct",
		data: {idProduct: idProduct},
		success: function(data) {}
	});
	
	$.ajax({
		type: "GET",
		url: "updateTotalPrice",
		success: function(data) {
			var newTotPrice = parseFloat(data);
			document.getElementById("totalPrice").innerHTML = newTotPrice.toFixed(2) + " €";
		}
	});
}

function updateFunctions(idProduct) {
	
	var selectProduct = "#productQuantity"+idProduct;
	var newQuantity = $(selectProduct).children("option:selected").val();

	for (var i = 0; i < cartProducts.length; i++){
		if(cartProducts[i] == idProduct){
			cartProductsQuantity[i] = newQuantity;
		}
	}

	$.ajax({
		type: "GET",
		url: "updateQuantity",
		data: {idProduct: idProduct, newQuantity: newQuantity},
		success: function(data) {
			var resp = data.split(" ");
				
			var divPrice = "productPrice"+idProduct;
			document.getElementById(divPrice).innerHTML = parseFloat(resp[0]).toFixed(2) + " €";
			$("#"+divPrice).css('color', '#c10d0d');
			
			var newTotPrice = parseFloat(resp[1]);
			document.getElementById("totalPrice").innerHTML = newTotPrice.toFixed(2) + " €";
		}
	});
}


$(document).ready(function(){
	 
    $("#radio2").click(function() {
        $("#shipment").show();
        $("#localStoreDropdown").hide();
    });
    $("#radio1").click(function() {
        $("#shipment").hide();
        $("#localStoreDropdown").show();
    });

});
