function lancerRecherche()
{
	var motCle = document.getElementById("inputMotCle").value;
	if(motCle.match(/^[a-z0-9_-]{3,30}$/) && motCle != "")
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