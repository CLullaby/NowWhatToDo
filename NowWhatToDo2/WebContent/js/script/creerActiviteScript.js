function envoyerFormulaire(){
	
	var domaine = document.getElementById("domaine").value;
	var activite = document.getElementById("activite").value;
	var lieu = document.getElementById("lieu").value;
	var adresse = document.getElementById("adresse").value;
	var ville = document.getElementById("ville").value;
	var codePostal = document.getElementById("codePostal").value;
	var siteWeb = document.getElementById("siteWeb").value;
	var telephone = document.getElementById("telephone").value;
	var email = document.getElementById("email").value;
	var description = document.getElementById("description").value;
	var lienPhoto = document.getElementById("lienPhoto").value;
	var importance = document.getElementById("importance").value;

	
	$.ajax({
        url: '../../CreerActivite',
        data: {
			domaine : domaine,
			activite : activite,
			lieu : lieu,
			adresse : adresse,
			ville : ville,
			codePostal : codePostal,
			siteWeb : siteWeb,
			telephone : telephone,
			email : email,
			description : description,
			lienPhoto : lienPhoto,
			importance : importance
        },
        async: false,
        type: 'POST',
        dataType: 'json',
        success: function (data) {
        	alert("Activité créee avec succès");
        	location.href = "adminActivite.html";
        },
        error: function (data) {
        	alert("Un problème est survenu, veuillez réessayer ultérieurement.");
        }
    });
}