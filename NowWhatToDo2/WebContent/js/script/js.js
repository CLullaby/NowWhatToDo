function recupererActivite(domaine)
{
	
	 var activiteTable = [];	
	 var cheminImage = "../../img/";
	 var arraySizeImages = buildArraySizeImages();
	 
	 $.ajax({
	        url: '../../RecupererActivite',
	        async: false,
	        type: 'GET',
	        data:{
	        	domaine: domaine
	        },
	        success: function (data) {	        	
	        	for(var i=0; i<data.length; i++){
	        	
	        		var activite = [];
	        		var jsonActivite = data[i];

	        		activite['nomActivite'] = jsonActivite.nomActivite;
	        		activite['description'] = jsonActivite.Description;
	        		//activite['Description'] = jsonActivite.Description;
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
}
	

function displayActivite(activiteTable,cheminImage,arraySizeImages){
	
	$.each(activiteTable,function(key,value){	
		
		var nomActivite = value['nomActivite'];
		var description = value['description']
		var nomLieu = value['nomLieu'];
		var adresse = value['adresse'];
		var codePostal = value['codePostal'];
		var ville = value['ville'];
		var siteWeb = value['siteWeb'];
		var telephone = value['telephone'];
		var email = value['email'];
		var domaine = value['domaine'];
		var lienPhoto = value['lienPhoto'];
		var importance = value['importance'];
		
//			
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
		
		var blocHtml = 
			 "<div class='col-lg-6 col-md-6 col-sm-6 mb'>"
				+ "<div class='darkblue-panel'>"
					if(nomActivite != "")
					{
						+"<div class='panel-heading'><div class=''>" + nomActivite + "</div>"
					}
					+"</div>"
				
					+"<div class='panel-body'>"	
						+ "<ul class='list-group'>"
					      	+"<li class=''>"
					      	if(nomLieu != "")
				      		{
					      		+ "Lieu : " + nomLieu 
				      		}
					      	if(adresse != "")
				      		{
					      		+ ", " + adresse 
				      		}
					      	if(codePostal != "")
				      		{
					      		+ " " + codePostal 
				      		}
					      	if(ville != "")
				      		{
					      		+ " " + ville 
				      		}				
					      	+"</li>"
					      	+"<li class=''>"
					      	if(telephone != "")
				      		{
					      		+ "Tel : " + telephone
				      		}
					      	if(email != "")
				      		{
					      		+ "   contact email : " + email
				      		}
					      	+"</li>"
					      	+"<li class=''>" 
					      	if(siteWeb != "")
				      		{
					      		+"<a class='btn btn-md btn-theme' href='" + siteWeb + "' target='blank'>Site internet</a>" 
					      	}
					      	+"</li>"
				      		
					      	+"<li class=''>"+
					      		"<div class='col-xs-9'>"
					      		if(description != "")
				      			{
					      			+ "<p class='text-justify'>" + description + "</p>"
				      			}
					      		+"</div>" 
					      		+"<div class='col-xs-3'>"
					      		if(lienPhoto != "")
				      			{
					      			+"<img src='"+ cheminImage + lienPhoto +"' width='"+arraySizeImages['SNCF']['width']+"' height='"+arraySizeImages['SNCF']['height']+"' alt=''/>";
				      			}
					      		+"</li>"
					    +"</ul>"
					+"</div>"
				+"</div>"
			+"</div>"
								
		;
		$(".news").append(blocHtml);
		
	});	
}

//Gere la taille des images
function buildArraySizeImages(){
	
	 var arraySizeImages = [];
	 var arraySizes = [];
	 
	 arraySizes["width"] = 100;
	 arraySizes["height"] = 60;
	 
	 arraySizeImages["SNCF"] = arraySizes;
	 
	 return arraySizeImages;
}
