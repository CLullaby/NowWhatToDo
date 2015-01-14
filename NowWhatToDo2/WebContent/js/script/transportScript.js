$(document).ready(function(){
	
	//$.get("../../RecupererActivite", {}, function(data, status) {});
	
	//passer en paramètre domaine transport
	
	 var activiteTable = [];	
	
	 $.ajax({
	        url: '../../RecupererActivite',
	        async: false,
	        type: 'GET',
	        data:{
	        	domaine:'transport'
	        },
	        success: function (data) {
	        	
	        	//alert(data);
	        	
	        	//$.each(data,function(key,value){
	        	
	        	for(var i=0; i<data.length; i++){
	        	
	        		var activite = [];
	        		var jsonActivite = data[i];
	        		
	        		//alert(jsonActivite);
	        		
	        		activite['nomActivite'] = jsonActivite.nomActivite;
	        		activite['Description'] = jsonActivite.Description;
	        		activite['nomLieu'] = jsonActivite.nomLieu;
	        		activite['adresse'] = jsonActivite.adresse;
	        		activite['ville'] = jsonActivite.ville;
	        		activite['codePostal'] = jsonActivite.codePostal;
	        		activite['siteWeb'] = jsonActivite.siteWeb;
	        		activite['telephone'] = jsonActivite.telephone;
	        		activite['email'] = jsonActivite.email;
	        		activite['domaine'] = jsonActivite.domaine;
	        		activite['lienPhoto'] = jsonActivite.lienPhoto;
	        		activite['importance'] = jsonActivite.importance;
	        		
	        		activiteTable.push(activite);
	        		
	        	}
	        	
	        	displayActivite(activiteTable);
	        },
	        error: function (data) {
	        	alert("Un problème est survenu, veuillez réessayer ultérieurement.");
	        }
	    });
	
});
	

	function displayActivite(activiteTable){
		
		$.each(activiteTable,function(key,value){	
			
			var stringDivActivite = 
										 "<div class='col-lg-6 col-md-6 col-sm-6 mb'>"
											+ "<div class='product-panel-2 pn'>"
												+"<div class='badge badge-hot'>"+value['nomActivite']+"</div>"
												+"<img src="+value['lienPhoto']+"width='180' alt=''/>"
												+"<h5 class='mt'>"+value['Description']+"</h5>"
												+"<p>Information au "+value['telephone']+" et "+value['email']+"</p>"
												+"<a class='btn btn-small btn-theme04' href="+value['siteWeb']+" target='blank'>Site internet</a>"

																	+"<p class=''>"+value['nomLieu']+"</p>"
																	+"<p class=''>"+value['adresse']+"</p>"
																	+"<p class=''>"+value['codePostal']+"</p>"
																	+"<p class=''>"+value['ville']+"</p>"
																
											+"</div>"
										+"</div>"
									
			;
			$(".news").append(stringDivActivite);
			
		});
		
		
	}




//	var arrayDiv = new Array();
//
//		$.post("myServlet", {
//					domaine : "transport",
//				}, function(data, status) {
//					$("#nameAuthentif").show();
//				});
//
//for(var i=0; i <= (nb de transport ds la table -1 ); i++){
//    arrayDiv[i] = document.createElement('div');
//    arrayDiv[i].id = 'transport' + i;
//    arrayDiv[i].className = 'transport';
//}
//document.body.appendChild(arrayDiv[0].appendChild(arrayDiv[1]));
//
//
//
