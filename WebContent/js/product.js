
function Review(text, author){
	this.text = text;
	this.author = author;
	var d = new Date();
	this.date = d.getDate()+"/"+(d.getMonth()+1)+"/"+d.getFullYear();
}

var reviewsOffset = 1;

function loadOtherReviews(productId){
	$.getJSON( "findreviews?id=" + productId + "&offset=" + reviewsOffset, function( data ) {
		if(data.length > 0) {
			$.each(data, function (key, val) {
				$("#reviewBox").append("<h6 id=\"reviewTitle\">" + val.title + "</h6>" +
					"<p id=\"reviewText\">" + val.body + "</p>" +
					"<small id=\"author\" class=\"text-muted\">Scritta da " + val.username + "</small>" +
					"<hr>")
			});
			reviewsOffset++;
		} else {
			$("#otherReviewsButton").hide();
		}
	});
}

function checkProduct(idProduct, page) {
	
	var quantity = $("#productQuantity").val();
	
	$.ajax({
		type: "GET",
		url: "checkCart",
		data: {idProduct: idProduct},
		success: function(data) {
			
			if((page == "product" && data == "inCart") || page == "cart") {
				document.getElementById("addToCart").disabled = true;
				$("#addToCart").html("Aggiunto al carrello");
			}
			
			else if(page == "product" && data != "inCart") {
				document.getElementById("addToCart").disabled = false;
				$("#addToCart").html("Aggiungi al carrello");
			}
			
			if(page == "cart") {
				if(data != "inCart") {
					$.ajax({
						type: "POST",
						url: "cart",
						data: {idProduct: idProduct, quantity: quantity},
						success: function(data){}
					});
				}
			}
		}
	});
}

$(document).ready(function(){
	  
	  $("#writeReview").click(function(){
		  $(".bg-modal").css("display", "flex");
	  });

	  $(".close").click(function(){
		  $(".bg-modal").css("display", "none");
	  });
});