function lancerRecherche()
{
	var activiteTable = [];
	var motCle = document.getElementById("inputMotCle").value;
	if(motCle.match(/^[A-Za-z0-9_-]{3,30}$/) && motCle != "")
	{
		$.ajax({
	        url: '../../Recherche',
	        data: {
	        	motCle : motCle
	        },
	        async: true,
	        type: 'POST',
	        dataType: 'json',
	        success: function (data) {
	        	//Pensez a dire si la liste est vide ici ou dans le JS
	        	
	        	//data.liste
	        	if(jQuery.isEmptyObject(data)){
	        		
	        		$('#messageVide').addClass('messageVide');
	        		var span = document.createElement("p");
	        		var text = document.createTextNode("Mot introuvable");
	        		span.appendChild(text);	
	            	//Permet de supprimer si un message est déja présent
	            	var elm = document.getElementById('messageVide');
	            	while (elm.firstChild) {
	            		  elm.removeChild(elm.firstChild);
	            	}
	        		elm.appendChild(span);
	        		
	        	}
	        	else{
	        		
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
	        	}
	        	
	        	
	        }
	    });
	}
	else
	{
		$('#messageErreur').addClass('messageErreur');
		var span = document.createElement("span");
		var text = document.createTextNode("Veuillez rentrez un mot clé d'au moins 3 caractères avant de lancer la recherche !");
		span.appendChild(text);	
    	//Permet de supprimer si un message est déja présent
    	var elm = document.getElementById('messageErreur');
    	while (elm.firstChild) {
    		  elm.removeChild(elm.firstChild);
    		}
		elm.appendChild(span);
	}

}

function displayActivite(activiteTable,cheminImage,arraySizeImages){
	
	$.each(activiteTable,function(key,value){	
		
		var stringNomActivite = value['nomActivite'];
		var stringNomLieu = value['nomLieu'];
		var stringAdresse = value['adresse'];
		var stringCodePostal = value['codePostal'];
		var stringVille = value['ville'];
		var stringTelephone = value['telephone'];
		var stringEmail = value['email'];
		var stringTelephoneEtEmail = "<p>"+stringTelephone+" et "+stringEmail;
		var stringSiteWeb = value['siteWeb'];
		var stringDescription = value['Description'];
		var stringLienPhoto = value['lienPhoto'];
		
		
		var stringDivActivite = 
									 
											+"<p>"+stringNomActivite+"</p>"
													+ "<ul class='list-group'>"
												      	+"<li class='list-group-item'>"+stringNomLieu+stringAdresse+stringCodePostal+stringVille+"</li>"
												      	+"<li class='list-group-item'>"+stringTelephoneEtEmail+"</li>"
												      	+"<li class='list-group-item'>"+stringSiteWeb+"</li>"
												    +"</ul>"
												    +"<p>"+stringDescription+"</p>"
												    +"</p>"stringLienPhoto+"</p>"
												    
								
		;
		$(".resultatRecherche").append(stringDivActivite);
	});

}
	