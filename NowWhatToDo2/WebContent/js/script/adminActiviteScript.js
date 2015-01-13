
//Au chargement de la page, affiche les activités sur la page d'administration
function afficherActivites(){
	getActivites();
	
}

//Récupère les Activités stockées dans la base de données
function getActivites(){
	$.ajax({
        url: '../../AdminActivite',
        async: false,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	var jsonArray = new Array();
        	jsonArray = data.listeActivites;
        	//Récupération du JSON de chaque activité et de chaque champ dans chacune des activités
        	for(i=0;i<jsonArray.length;i++){
        		var activiteJson = jsonArray[i];

        		var id = activiteJson.id;
        		var nomActivite = activiteJson.nomActivite;
        		var description = activiteJson.description;
        		var nomLieu = activiteJson.nomLieu;
        		var adresse = activiteJson.adresse;
        		var ville = activiteJson.ville;
        		var codePostal = activiteJson.codePostal;
        		var siteWeb = activiteJson.siteWeb;
        		var telephone = activiteJson.telephone;
        		var email = activiteJson.email;
        		var domaine = activiteJson.domaine;
        		var lienPhoto = activiteJson.lienPhoto;
        		var importance = activiteJson.importance;
        		
        		//Récupération du tableau dans la page HTML 
        		var tableauHtml = document.getElementById("tableauActivites");
        		
        		// Ajout d'une ligne
        		tableauHtml.innerHTML += "<tr id=ligne" + id + ">";
        		var ligne = document.getElementById("ligne" + id);
        		
        		// R�cup�ration et �criture de chaque champ dans le tableau HTML
        		var colonneDomaine = ligne.insertCell(-1);// on a une ajout� une cellule
        		colonneDomaine.innerHTML += "<td>" + domaine +"</td>";

        		var colonneActivite = ligne.insertCell(1);// on ajoute la seconde cellule
        		colonneActivite.innerHTML += "<td>" + nomActivite +"</td>";

        		var colonneLieu = ligne.insertCell(2);
        		colonneLieu.innerHTML += "<td>" + nomLieu +"</td>";

        		var colonneAdresse = ligne.insertCell(3);
        		colonneAdresse.innerHTML += "<td>" + adresse +"</td>";
        		
        		var colonneVille = ligne.insertCell(4);
        		colonneVille.innerHTML += "<td>" + ville +"</td>";
        		
        		var colonneCodePostal = ligne.insertCell(5);
        		colonneCodePostal.innerHTML += "<td>" + codePostal +"</td>";
        		
        		var colonneSiteWeb = ligne.insertCell(6);
        		colonneSiteWeb.innerHTML += "<td>" + siteWeb +"</td>";
        		
        		var colonneTelephone = ligne.insertCell(7);
        		colonneTelephone.innerHTML += "<td>" + telephone +"</td>";
        		
        		var colonneEmail = ligne.insertCell(8);
        		colonneEmail.innerHTML += "<td>" + email +"</td>";
        		
        		var colonneDescription = ligne.insertCell(9);
        		colonneDescription.innerHTML += "<td>" + description +"</td>";
        		
        		
        		var colonneImportance = ligne.insertCell(10);
        		colonneImportance.innerHTML += "<td>" + importance +"</td>";
        	
        		var colonneBoutons = ligne.insertCell(11);
        		colonneBoutons.innerHTML += "<td>" + "<button type=\"submit\" class =\"btn btn-danger btn-sm\" onclick=\"supprimerActivite()\">"+"</td>";
        		
//        		<td><button type="submit" class="btn btn-danger btn-sm"
//					onclick="supprimerActivite(1)">
//					<span class="glyphicon glyphicon-remove"></span>
//				</button>
//				<button type="submit" class="btn btn-success btn-sm"
//					onclick="modifierActivite(1)">
//					<span class="glyphicon glyphicon-pencil"></span>
//				</button></td>
        		
        		// Fermeture de la balise ligne
        		tab.innerHTML += "</tr>";
        	}
        },
        error: function (data) {
        	alert("Les activités n'ont pas pu être chargées correctement.");
        }
    });
}

function supprimerActivite(idActivite){
	
}

function modifierActivite(idActivite){
	
}