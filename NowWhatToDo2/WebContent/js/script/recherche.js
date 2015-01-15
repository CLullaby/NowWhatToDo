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
		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.nomActivite)){
//			        		activite['nomActivite'] = jsonActivite.nomActivite;
//		        		}
//		        		else{
//		        			activite['nomActivite'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.Description)){
//			        		activite['Description'] = jsonActivite.Description;
//		        		}
//		        		else{
//		        			activite['Description'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.nomLieu)){
//			        		activite['nomLieu'] = jsonActivite.nomLieu;
//		        		}
//		        		else{
//		        			activite['nomLieu'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.adresse)){
//			        		activite['adresse'] = jsonActivite.adresse;
//		        		}
//		        		else{
//		        			activite['adresse'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.ville)){
//			        		activite['ville'] = jsonActivite.ville;
//		        		}
//		        		else{
//		        			activite['ville'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.codePostal)){
//			        		activite['codePostal'] = jsonActivite.codePostal;
//		        		}
//		        		else{
//		        			activite['codePostal'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.siteWeb)){
//			        		activite['siteWeb'] = jsonActivite.siteWeb;
//		        		}
//		        		else{
//		        			activite['siteWeb'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.telephone)){
//			        		activite['telephone'] = jsonActivite.telephone;
//		        		}
//		        		else{
//		        			activite['telephone'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.email)){
//			        		activite['email'] = jsonActivite.email;
//		        		}
//		        		else{
//		        			activite['email'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.domaine)){
//			        		activite['domaine'] = jsonActivite.domaine;
//		        		}
//		        		else{
//		        			activite['domaine'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.lienPhoto)){
//			        		activite['lienPhoto'] = jsonActivite.lienPhoto;
//		        		}
//		        		else{
//		        			activite['lienPhoto'] = "";
//		        		}
//		        		
//		        		if(jQuery.isEmptyObject(jsonActivite.importance)){
//			        		activite['importance'] = jsonActivite.importance;
//		        		}
//		        		else{
//		        			activite['importance'] = "";
//		        		}
//		        		
//		        		activiteTable.push(activite);
//		        	
//	        		}
	        		
	        		
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
	        		
	        		for(var k=0; k<activiteTable.length; k++){
	        			
	        				alert(k+" "+activiteTable[k]['nomActivite']);
	        				alert(k+" "+activiteTable[k]['Description']);
	        				alert(k+" "+activiteTable[k]['nomLieu']);
	        				alert(k+" "+activiteTable[k]['adresse']);
	        				alert(k+" "+activiteTable[k]['ville']);
	        				alert(k+" "+activiteTable[k]['codePostal']);
	        				alert(k+" "+activiteTable[k]['siteWeb']);
	        				alert(k+" "+activiteTable[k]['telephone']);
	        				alert(k+" "+activiteTable[k]['email']);
	        				alert(k+" "+activiteTable[k]['domaine']);
	        				alert(k+" "+activiteTable[k]['lienPhoto']);
	        				alert(k+" "+activiteTable[k]['importance']);
	        			
	        		}
	        		
	        		
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


	