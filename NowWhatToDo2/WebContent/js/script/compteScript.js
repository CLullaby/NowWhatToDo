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
	        	//alert("Votre informations ont été modifiées avec success");
	        	//location.href = "compte.html";
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