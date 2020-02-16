
<div class="modal fade" id="modal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" data-backdrop="false">
  <div class="modal-dialog" role="document">
    <div id="modalContent" class="modal-content">
      <div class="modal-header">
      	<c:if test="${administrator != null}">
      		<script>$(".modal-content").css('background-color', '#0080ff')</script>
        	<h5 class="modal-title"">Benvenuto, ${administrator.id}</h5>
        </c:if>
        <c:if test="${user != null}">
        	<script>$(".modal-content").css('background-color', '#feac01')</script>
        	<h5 class="modal-title">Benvenuto, ${user.username}</h5>
        </c:if>
      </div>
      <div class="modal-body">
        Ti auguriamo una felice navigazione
      </div>
    </div>
  </div>
</div>

<div id="menuBar" class="row align-items-center">
	
	<a href="home" class="col">
	<div id="logo"></div>
	</a>
	
	<div id="whoWeAre" class="col">
		<a href="whoWeAre">
			Chi Siamo
		</a>
	</div>
	<div id="faq" class="col">
		<a href="faq">
			FAQ
		</a>
	</div>
	<div id="map" class="col">
		<a href="map">
			Mappa
		</a>
	</div>
	<c:if test="${user != null}">
		<div id="orders" class="col">
			<a href="orders">
				I miei ordini
			</a>
		</div>	
	</c:if>
	
	 <div id="containerSearchBar">
      <div class="d-flex justify-content-center">
        <div class="searchbar">
          <input class="search_input col-sm-9" type="text" name="" placeholder="Cerca...">
          <a class="search_icon col-sm-3" id="searchButton"><i class="fa fa-search"></i></a>
        </div>
      </div>
    </div>
    <c:if test="${administrator == null}">
	    <div class = "col">
	    	<a href="cart">
				<button type="button" class="btn" id="cart">Carrello <i style="color: white;" class="fa fa-shopping-cart" aria-hidden="true"></i></button>
			</a>
		</div>
	</c:if>
    <c:choose>
		<c:when test="${(user == null) && (administrator == null)}">
			<div class="col">
				<a href="registration">
					<button id="register" class="btn btn-outline-secondary">Registrati</button>
				</a>
			</div>
		</c:when>
		<c:otherwise>
			<c:if test="${firstLogin != null}">
			<c:remove var="firstLogin" scope="session" />
				<script>
					$("#modal").modal();
					
					 setTimeout(function(){
					  $('#modal').modal('hide')
					 }, 2000);  
					 
					
				</script>
			</c:if>
			
			<c:if test="${administrator != null}">
				<div class="col">
					<a href="insertproduct">
						<button id="buttonAddProduct" class="btn btn">
							Aggiungi Prodotto
						</button>
					</a>
				</div>
			</c:if>
		</c:otherwise>
	</c:choose>
	
	<div class="col">
		<c:if test="${(user == null) && (administrator == null)}">
			<a href="login">
				<button id="login" class="btn btn-primary">
					Accedi
				</button>
			</a>
		</c:if>
		<c:if test="${(user != null) || (administrator != null)}">
			<a href="login?logout=true" onclick="signOut();"> 
				
				<button id="logout" class="btn btn-primary">
					Logout
				</button>	
			</a>
			
		</c:if>
				
		<script>
		  function signOut() {
		    var auth2 = gapi.auth2.getAuthInstance();
		    auth2.signOut().then(function () {
		      console.log('User signed out.');
		    });
		  }
		  
		  function onLoad() {
		      gapi.load('auth2', function() {
		        gapi.auth2.init();
		      });
		   }
		</script>
				
				
				
				
				
  
  

  
	</div>
	
</div>