//Les champs ont déja été vérifiés en js donc on va envoyer à la Servlet les données en format JSON
//On gère la réponse de la Servlet avec une alert + redirection
function ajaxInscription()
{
	var login = document.getElementById("login").value;
	var mdp = document.getElementById("password").value;
	var mdpBis = document.getElementById("passwordBis").value;
	var email = document.getElementById("email").value;
	var nom = document.getElementById("nom").value;
	var prenom = document.getElementById("prenom").value;
	var age = document.getElementById("age").value;
	var adresse = document.getElementById("adresse").value;
	var codePostal = document.getElementById("codePostal").value;
	var telephone = document.getElementById("telephone").value;

	if(login != "" && mdp != "" && mdpBis != "" && email != "" && nom != "" && prenom != "" && age != "" && adresse != "" && codePostal != "" && telephone != ""
		&& verifNom(document.getElementById("login")) && verifMdp(document.getElementById("password")) && verifMdpBis(document.getElementById("passwordBis")) &&
		verifEmail(document.getElementById("email")) && verifNom(document.getElementById("nom")) && verifNom(document.getElementById("prenom")) &&
		verifAge(document.getElementById("age")) && verifString(document.getElementById("adresse")) && verifCodePostal(document.getElementById("codePostal")) &&
		verifTelephone(document.getElementById("telephone")) )
	{
		$.ajax({
	        url: '../../Inscription',
	        data: {
				login : login,
				mdp : mdp,
				mdpBis : mdpBis,
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
	        	alert("Votre compte a été crée avec success");
	        	location.href = "login.html";
	        },
	        error: function (data) {
	        	alert("Un problème est survenu, veuillez réessayer ultérieurement.");
	        }
	    });
	}
	else
	{
		alert("Veuillez remplir le formulaire !");
	}
    /*Non testé mais méthode similaire
	$.post("../../Inscription",	
			{
				login : login,
				mdp : mdp,
				mdpBis : mdpBis,
				email : email,
				nom : nom,
				prenom : prenom,
				adresse : adresse,
				telephone : telephone
			},
			function(data,status){
				return true;
			}	
	);*/
}
