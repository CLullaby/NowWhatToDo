$(document).ready(function(){

	$.ajax({
        url: '../../CompteAvancement',
        data: {
        	domaine : "administration"
        },
        async: true,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	
        		var activiteTable=[]
        		
        		//On construit une liste de tableaux d'activité
        		for(var i=0; i<data.length; i++){
    	        	
	        		var activite = [];
	        		var jsonActivite = data[i];
	        		
	        		
	        		activiteTable.push(activite);	
        		}
        		
        		//Affichage du résultat de la recherche dans le div 
        		afficherBlocsTache($("#blocTacheDone"),$("#blocTacheCours"),$("#blocTacheToDo"),activiteTable);
        	}
        
    });
});

function afficherBlocsTache(divTacheDone,divTacheCours,divTacheCours,activiteTable){
	
	
	
	
	
	
	
	
};

//Met a jour les données du formulaire
function ajaxMiseAJour()
{
	var login = document.getElementById("login").value;
	var email = document.getElementById("email").value;
	var nom = document.getElementById("nom").value;
	var prenom = document.getElementById("prenom").value;
	var age = document.getElementById("age").value;
	var adresse = document.getElementById("adresse").value;
	var codePostal = document.getElementById("codePostal").value;
	var telephone = document.getElementById("telephone").value;

	if(login != "" && email != "" && nom != "" && prenom != "" && age != "" && adresse != "" && codePostal != "" && telephone != ""
		&& verifNom(document.getElementById("login")) && verifEmail(document.getElementById("email")) && verifNom(document.getElementById("nom")) && verifNom(document.getElementById("prenom")) &&
		verifAge(document.getElementById("age")) && verifString(document.getElementById("adresse")) && verifCodePostal(document.getElementById("codePostal")) &&
		verifTelephone(document.getElementById("telephone")) )
	{
		$.ajax({
	        url: '../../Compte',
	        data: {
				login : login,
				email : email,
				nom : nom,
				prenom : prenom,
				age : age,
				adresse : adresse,
				codePostal : codePostal,
				telephone : telephone
	        },
	        async: false,
	        type: 'POST',
	        dataType: 'json',
	        success: function (data) {
	        	//alert("Vos informations ont été modifiées avec success");
	        	location.href = "compte.html";
	        },
	        error: function (data) {
	        	alert("Un problème est survenu, veuillez réessayer ultérieurement.");
	        }
	    });
	}
	else
	{
		alert("Veuillez remplir tout le formulaire !");
	}
}

//Récupère les infos du formulaire dans la BD
function getDonnees()
{
	$.ajax({
        url: '../../Compte',
        data: {

        },
        async: false,
        type: 'GET',
        dataType: 'json',
        success: function (data) {
        	//Verifie le log
        	if(data.etat == "loge")
        	{
        		//Affiche les données chargées
        		var inputLogin = document.getElementById("login");
        		var inputEmail = document.getElementById("email");
        		var inputNom = document.getElementById("nom");
        		var inputPrenom = document.getElementById("prenom");
        		var inputAge = document.getElementById("age");
        		var inputAdresse = document.getElementById("adresse");
        		var inputCodePostal = document.getElementById("codePostal");
        		var inputTel = document.getElementById("telephone");
        		
        		inputLogin.value = data.login;
        		inputEmail.value = data.email;
        		inputNom.value = data.nom;
        		inputPrenom.value = data.prenom;
        		inputAge.value = data.age;
        		inputAdresse.value = data.adresse;
        		inputCodePostal.value = data.codePostal;
        		inputTel.value = data.tel;
        		
        		
        	}
        	else
    		{
        		location.href = "login.html";
    		}	
        },
        error: function (data) {
        	alert("Un problème est survenu, veuillez recharger la page.");
        }
    });
}

