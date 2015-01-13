function ajaxLoginAdmin()
{
	var login = $('#login').val();
	var mdp = $('#mdp').val();
	
	//Regex pour vérifier que les champs ont été remplis (équivalent des fonctions dans verificationFonctionScript.js) car
	//pb de ces fonctions avec JQuery
	if(login != "" && mdp != "")
	{
		if(login.match(/^[a-z0-9_-]{2,30}$/))
		{	
			if(mdp.match(/^[a-z0-9_-]{2,15}$/))
			{
				$.ajax({
			        url: '../../Login',
			        data: {
						login : login,
						mdp : mdp
			        },
			        async: false,
			        type: 'GET',
			        dataType: 'json'
			    });
				
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
}