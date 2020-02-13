function infoWindow(id, marker, map) {
	
	var info;
	if(id == 1) {
		info = new google.maps.InfoWindow({
			content: 'Store Cosenza'
		});
	}
	
	if(id == 2) {
		 info = new google.maps.InfoWindow({
			content: 'Store Catanzaro'
		});
	}
	
	if(id == 3) {
		 info = new google.maps.InfoWindow({
			content: 'Store Crotone'
		});
	}
	
	if(id == 4) {
		 info = new google.maps.InfoWindow({
			content: 'Store Vibo Valentia'
		});
	}
	
	if(id == 5) {
		 info = new google.maps.InfoWindow({
			content: 'Store Reggio Calabria'
		});
	}
	info.open(map,marker);

}

function loadMarkers(coords) {
	
	var mapProp= {
	  center: new google.maps.LatLng(39.0202085,16.2472732),
	  zoom: 8.2,
	};
			
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
	
	var list = JSON.parse(coords);
	var id = 1;
	var markers = [];
	for(let i=0; i<list.length-1; i+=2) {
		var parsedLocation = new google.maps.LatLng(list[i],list[i+1]);
		var marker = new google.maps.Marker({
		    position: parsedLocation,
		    map: map,
		    title: 'Clicca per zoomare'
		  });
		markers.push(marker);
	}
	
	for(let i=0; i<markers.length; i++) {
		markers[i].addListener('click', function() {
		    map.setZoom(18);
		    map.setCenter(markers[i].getPosition());
		 });
		
		markers[i].addListener('mouseover', function() {
			infoWindow(i+1,markers[i],map);
		});
	}
}

function myMap() {
	
	var mapProp= {
	  center: new google.maps.LatLng(39.0202085,16.2472732),
	  zoom: 8.2,
	};
	
	var map = new google.maps.Map(document.getElementById("googleMap"),mapProp);
}