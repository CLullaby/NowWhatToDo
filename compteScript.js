//Verifier les champs en js 

//Colorie le champ si incorrect
function surligne(champ, erreur) {
    if (erreur)
        champ.style.backgroundColor = "#fba";
    else
        champ.style.backgroundColor = "";
}

//Indique si le champ est correct 
function verifNom(champ) {
    if (champ.value.length < 2 || champ.value.length > 30) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifString(champ) {
    if (champ.value.length < 2 || champ.value.length > 100) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifEmail(champ) {
    var regex = /^[a-zA-Z0-9._-]+@[a-z0-9._-]{2,}\.[a-z]{2,4}$/;
    if (!regex.test(champ.value)) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifCodePostal(champ)
{
    if (champ.value.length < 0 || champ.value.length > 5) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifAge(champ) {
    if (champ.value < 0 || champ.value > 150) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}

function verifTelephone(champ)
{
    if (champ.value.length < 0 || champ.value.length > 20) {
        surligne(champ, true);
        return false;
    }
    else {
        surligne(champ, false);
        return true;
    }
}




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

	$.ajax({
        url: '../../Inscription',
        data: {
			login : login,
			mdp : mdp,
			mdpBis : mdpBis,
			email : email,
			nom : nom,
			prenom : prenom,
			adresse : adresse,
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
