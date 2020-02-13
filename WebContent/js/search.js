/**
 * 
 */

$(document).ready(function(){
  
	
	/*set the price range*/
	$("#apply").click(function() {
		updateProducts(1);
	});
	
	/*set the brand*/
	$(".list-group-item").click(function(){
		if($(this).attr("id") != "prodActive"){
			$("#prodActive").attr("id", "not");
			$(".list-group-item").removeClass("active");
			$(".list-group-item").children().css("background-color", "#007bff");
			$(this).addClass("active");
			$(this).attr("id", "prodActive")
			$(this).children().css("background-color", "#feac01");
		}
		else {
			$("#prodActive").attr("id", "not");
			$(".list-group-item").removeClass("active");
			$(this).children().css("background-color", "#007bff");
		}
		
		
		updateProducts(1);
	});
  
});

function createPagination(numPages, actualPage){
	$("#paginationDiv").html("");
	$("#paginationDiv").append("<ul class=\"pagination justify-content-center\" id=\"pagination\">");
	$("#pagination").append("<li class=\"page-item\"><a class=\"page-link\" id=\"previous\">Precedente</a></li>");
	$("#previous").attr("onclick", "changePage(false,"+numPages+")");
	for(var i = 1; i<= numPages; i++){
		if(i==actualPage){
			$("#pagination").append("<li class=\"page-item active\" id=\"page"+i+"\"><a onclick=\"goToPage("+i+")\" class=\"page-link active\" id=\"a"+i+"\">"+i+"</a></li>");
		}
		else{
			$("#pagination").append("<li class=\"page-item\" id=\"page"+i+"\"><a onclick=\"goToPage("+i+")\" class=\"page-link\" id=\"a"+i+"\">"+i+"</a></li>");
		}
	}
	$("#pagination").append("<li class=\"page-item\"><a class=\"page-link\" id=\"next\">Successiva</a></li>");
	$("#next").attr("onclick", "changePage(true,"+numPages+")");


	
	
	
}

function createProduct(id, img, manufacturer, model, price, description, starsAvg){
	$("#productBox").append("<div id=product"+id+" class=\"col-lg-4 col-md-6 mb-4\">");
    $("#product"+id).append("<div id=\"card"+id+"\" class=\"card h-100\">");
    $("#card"+id).append("<a href=\"product?id="+id+"\"><img class=\"card-img-top img-thumbnail\" src=img/products/"+img+" alt=\"\"></a>");
    $("#card"+id).append("<div id=\"card-body"+id+"\" class=\"card-body\">");
    $("#card-body"+id).append("<h4 class=\"card-title\"><a href=\"product?id="+id+"\">"+manufacturer+" "+model+"</a></h4><h5>"+price+"€</h5><p class=\"card-text\">"+description+"</p>");
    $("#card"+id).append("<div class=\"card-footer\"><small id=\"stars"+id+"\" class=\"text-muted\"></small></div>");
   
    for(var i = 1; i<=5; i++){
        if(i<=starsAvg){
        	$("#stars"+id).html($("#stars"+id).html()+"&#9733;");
        }
        else {
        	$("#stars"+id).html($("#stars"+id).html()+"&#9734;");
        }
    }
    $("#stars"+id).css("margin-left", "0");	
}


function updateProducts(page){
	var keyword = (window.location.href.match(/keyword=.*/g));
	var categoryID = $("#categoryId").text();
	var orderBy = $("#orderBy").val();
	var filterBy = $("#prodActive").html();
	if(filterBy != undefined){
		var n = filterBy.search("<");
		filterBy = filterBy.substr(0, n);
	}
	else{
		filterBy = "";
	}
	var priceLower = ($(".wrunner__valueNote")[0].innerText);
	var priceUpper = ($(".wrunner__valueNote")[1].innerText);
	
	if(orderBy == "Consigliati"){
		orderBy = "stars";
	}
	else if(orderBy == "Prezzo crescente"){
		orderBy = "priceAsc";
	}
	else if(orderBy == "Prezzo decrescente"){
		orderBy = "priceDesc";
	}
	$("#productBox").html("");
	if(keyword != null){
		console.log(keyword);
		$.get("searchjson?"+keyword+"&p="+page+"&orderBy="+orderBy+"&filterBy="+filterBy+"&priceFrom="+priceLower+"&priceTo="+priceUpper, function(responseJson) {         
		    $.each(responseJson, function(index, product) { 
		    	if(index == 0){
		    		createPagination(product.pages, page);
		    	}
		    	else{
		    		createProduct(product.id, product.imagePath, product.manufacturer, product.model, product.price, product.description, product.starsAvg);
		    	}
		    });
		});
	}
	else {
		$.get("searchjson?category="+categoryID+"&p="+page+"&orderBy="+orderBy+"&filterBy="+filterBy+"&priceFrom="+priceLower+"&priceTo="+priceUpper, function(responseJson) {         
		    $.each(responseJson, function(index, product) { 
		    	if(index == 0){
		    		createPagination(product.pages, page);
		    	}else{
		    		createProduct(product.id, product.imagePath, product.manufacturer, product.model, product.price, product.description, product.starsAvg);
		    	}
		    });
		});
	}
	
}




function goToPage(page){ 
	
	
	var actualPage = $(".page-item.active").text();
	console.log(page);
	if(actualPage != page){
		var pageSel = "#page"+actualPage;
		var aSel = "#a"+actualPage;
		var actualPage = parseInt(actualPage, 10);
		$(pageSel).removeClass();
		$(aSel).removeClass();
		$(pageSel).addClass("page-item");
		$(aSel).addClass("page-link");
		actualA = "#a"+page;
		actualPage = "#page"+page;
		$(actualPage).addClass("active");
		$(actualA).addClass("active");
		
		updateProducts(page);
	} 
	
}


function changePage(next, totalPages) {
	var outcome = false;
	var actualPage = $(".page-item.active").text();
	var pageSel = "#page"+actualPage;
	var aSel = "#a"+actualPage;
	var actualPage = parseInt(actualPage, 10);
	if(next === true){
		if((actualPage+1) <= totalPages){
			$(pageSel).removeClass();
			$(aSel).removeClass();
			$(aSel).addClass("page-link");
			$(pageSel).addClass("page-item");
			pageSel = "#page"+(actualPage+1);
			aSel  = "#a"+(actualPage+1);
			$(pageSel).addClass("active");
			$(aSel).addClass("active");
			outcome = true;
		}
	}
	else {
		if ((actualPage - 1) >= 1){
			$(pageSel).removeClass();
			$(aSel).removeClass();
			$(pageSel).addClass("page-item");
			$(aSel).addClass("page-link");
			pageSel = "#page"+(actualPage-1);
			aSel  = "#a"+(actualPage-1);
			$(pageSel).addClass("active");
			$(aSel).addClass("active");
			outcome = true;
			
		}
	}
	actualPage = $("a.active").text();
	
	if(outcome === true){
		updateProducts(actualPage);
	}
	
}


