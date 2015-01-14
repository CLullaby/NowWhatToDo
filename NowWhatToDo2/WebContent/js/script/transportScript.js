$(document).ready(function(){
	
	//$.get("../../RecupererActivite", {}, function(data, status) {});
	
	//passer en paramètre domaine transport
	
	 var activiteTable = [];	
	 var cheminImage = "../../img/";
	 var arraySizeImages = buildArraySizeImages();
	 
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
	        	
	        	displayActivite(activiteTable,cheminImage,arraySizeImages);
	        },
	        error: function (data) {
	        	alert("Un problème est survenu, veuillez réessayer ultérieurement.");
	        }
	    });
	
});
	

	function displayActivite(activiteTable,cheminImage,arraySizeImages){
		
		$.each(activiteTable,function(key,value){	
			
			var stringNomActivite = " ";
			if(value['nomActivite'] != ""){
				stringNomActivite = "<div class='badge badge-hot'>"+value['nomActivite']+"</div>";
			}
			
			var stringLienPhoto = " ";
			if(value['lienPhoto'] != ""){
				stringLienPhoto = "</td><td><img src='"+cheminImage+value['lienPhoto']+"' width='"+arraySizeImages['SNCF']['width']+"' height='"+arraySizeImages['SNCF']['height']+"' alt='image'/></td></tr>";
			}
			
			var stringDescription = " ";
			if(value['Description'] != ""){
				stringDescription = "<p class='text-justify'>"+value['Description']+"</p>";
			}
			
			var stringDescriptionPhoto = " ";
			if(stringDescription != " " && stringLienPhoto != " "){
				stringDescriptionPhoto = "<table class='table'><tr><td>"+stringDescription+stringLienPhoto+"</table>";
			}
				
			var stringTelephoneEtEmail = " ";
			if(value['telephone'] != "" && value['email'] != ""){
				stringTelephoneEtEmail = "<li class='list-group-item'>"+"<p class='text-justify'>Informations au "+value['telephone']+" ou par mail "+value['email']+"</p></li>";
			}
			
			var stringSiteWeb = " ";
			if(value['siteWeb'] != ""){
				stringSiteWeb = "<li class='list-group-item'><a class='btn btn-md btn-theme04' href="+value['siteWeb']+" target='blank'>Site internet</a></li>";
			}
			
			var stringNomLieu = " ";
			if(value['nomLieu'] != ""){
				stringNomLieu = "<p class='text-justify'>"+value['nomLieu'];
			}
			
			var stringAdresse = " ";
			if(value['adresse'] != ""){
				stringAdresse = " "+value['adresse'];
			}
			
			var stringCodePostal = " ";
			if(value['codePostal'] != ""){
				stringCodePostal = " "+value['codePostal'];
			}
			
			var stringVille = " ";
			if(value['ville'] != ""){
				stringVille = " "+value['ville']+"</p>";
			}
//			
			var stringViewInfosLieu = " ";
			var stringTotalEmplacementLieux = stringNomLieu+stringAdresse+stringCodePostal+stringVille;
			var stringTotalContactLieux = "</li>"+stringTelephoneEtEmail+stringSiteWeb+"</ul>";
			var stringTotalInfosLieu = stringTotalEmplacementLieux+stringTotalContactLieux;
			if(stringTotalInfosLieu != " "){
				stringViewInfosLieu = "<ul class='list-group'><li class='list-group-item'>"+stringTotalInfosLieu;
			}
			
			var stringPanelBody = " ";
			var stringBodyInfos = stringTotalInfosLieu+stringDescriptionPhoto; 
			if(stringBodyInfos != " "){
				stringPanelBody = "<div class='panel-body'>"+stringBodyInfos+"</div>";
			}
			
//			"<div class='col-lg-6 col-md-6 col-sm-6 mb'>"
//			+ "<div class='product-panel-2 pn' style='padding:0px 25px 0px 25px;'>"
//				+"<div class='row'>"+stringNomActivite+"</div>"
//				+"<div class='row'>"+stringNomLieu+stringAdresse+stringCodePostal+stringVille+"</div>"
//				+"<div class='row'>"+stringTelephoneEtEmail+"</div>"
//				+"<div class='row'>"+stringSiteWeb+"</div>"
//				+"<div class='row'>"+stringDescription+"</div>"
//				+"<div class='row'>"+stringLienPhoto+"</div>"
//				
//
//								
//			+"</div>"
//		+"</div>"
			
			var stringDivActivite = 
										 "<div class='col-lg-6 col-md-6 col-sm-6 mb'>"
											+ "<div class='darkblue-panel'>"
													+"<div class='panel-heading'>"+stringNomActivite+"</div>"
													+stringPanelBody	
											+"</div>"
										+"</div>"
									
			;
			$(".news").append(stringDivActivite);
			
		});
		
		
		
	}
	
	function buildArraySizeImages(){
		
		 var arraySizeImages = [];
		 var arraySizes = [];
		 
		 arraySizes["width"] = 100;
		 arraySizes["height"] = 60;
		 
		 arraySizeImages["SNCF"] = arraySizes;
		 
		 return arraySizeImages;
		 
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
