$(document).ready(function(){
	
	$("#send_login").on('click',function()
	{
		var identifiant = $('#form_login :input[name="identifiant"]').val();
		var mdp = $('#form_login :input[name="motDePasse"]').val();
		
		//Regex pour vérifier que les champs ont été remplis (équivalent des fonctions dans verificationFonctionScript.js) car
		//pb de ces fonctions avec JQuery
		if(identifiant != "" && mdp != "")
		{
			if(identifiant.match(/^[a-z0-9_-]{2,30}$/))
			{	
				if(mdp.match(/^[a-z0-9_-]{2,15}$/))
				{
					 var jsonTable = [];	
					 var identificationForm = {
							 identifiant: identifiant,
							 motDePasse: mdp
					 }
					 
					jsonTable.push(identificationForm);
					
					 //méthode envoie et retour de tableau de json
					$.post("../../Login",
							{
								identificationForm: JSON.stringify(jsonTable)
							},
							function(data,status){
								alert(data.resultat);
							}
							//Pensez a renvoyer le type d'erreur si l'authentification est false
							
									
					);
				}
				else
				{
					alert("Le mot de passe comporte trop de caractères !");
				}
			}
			else{
				alert("Le login comporte trop de caractères !");
			}
		}
		else
		{
			alert("Veuillez remplir les champs !");
		}
	});
});